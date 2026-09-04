package com.example.util

import android.util.Log
import com.example.model.SubscriptionRecord
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.tasks.await

object SubscriptionHistoryManager {

    private const val TAG = "SubscriptionHistory"

    suspend fun getHistory(userId: String? = null, userEmail: String? = null): List<SubscriptionRecord> {
        val targetUid = userId
            ?: AuthManager.getAuth()?.currentUser?.uid
            ?: FirebaseAuth.getInstance().currentUser?.uid
            ?: return emptyList()

        val targetEmail = (userEmail
            ?: AuthManager.getAuth()?.currentUser?.email
            ?: FirebaseAuth.getInstance().currentUser?.email)?.trim()

        val db = FirebaseFirestore.getInstance()
        val records = mutableListOf<SubscriptionRecord>()
        val seenIds = mutableSetOf<String>()

        // 1. Fetch from primary user's subscription_history subcollection
        try {
            val snapshot = db.collection("users").document(targetUid)
                .collection("subscription_history")
                .get()
                .await()

            for (doc in snapshot.documents) {
                SubscriptionRecord.fromDocument(doc)?.let { record ->
                    if (seenIds.add(record.id)) {
                        records.add(record)
                    }
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error fetching subscription history for uid: $targetUid", e)
        }

        // 2. Cross-reference other UID documents that share the same email (e.g. duplicate accounts)
        if (!targetEmail.isNullOrBlank()) {
            try {
                val emailMatches = db.collection("users")
                    .whereEqualTo("email", targetEmail)
                    .get()
                    .await()

                for (userDoc in emailMatches.documents) {
                    if (userDoc.id != targetUid) {
                        val otherSubSnap = db.collection("users").document(userDoc.id)
                            .collection("subscription_history")
                            .get()
                            .await()

                        for (doc in otherSubSnap.documents) {
                            SubscriptionRecord.fromDocument(doc)?.let { record ->
                                if (seenIds.add(record.id)) {
                                    records.add(record)
                                }
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                Log.w(TAG, "Error cross-referencing subscription history by email: $targetEmail", e)
            }
        }

        // 3. Fallback: If no records were found in the subcollection, inspect the user document itself
        // for an active/lifetime subscription and construct an active record so the user never sees a false empty state.
        if (records.isEmpty()) {
            try {
                val userDoc = db.collection("users").document(targetUid).get().await()
                if (userDoc.exists()) {
                    val role = userDoc.getString("role") ?: "free"
                    val premiumUntil = userDoc.getLong("premiumUntil")
                    val isLifetime = role.equals("premium", ignoreCase = true) && (premiumUntil == null || premiumUntil == 0L)
                    val isTimeLimitedActive = premiumUntil != null && premiumUntil > System.currentTimeMillis()

                    if (isLifetime || isTimeLimitedActive) {
                        val duration = if (isLifetime) 0L else ((premiumUntil ?: 0L) - System.currentTimeMillis()).coerceAtLeast(0L)
                        val planTitle = if (isLifetime) "Suscripción Premium (Vitalicia)" else "Suscripción Premium Activa"
                        val syntheticRecord = SubscriptionRecord(
                            id = "active_profile_sub",
                            timestamp = userDoc.getLong("last_active") ?: System.currentTimeMillis(),
                            durationMillis = duration,
                            planName = planTitle,
                            status = "Completado",
                            amount = "$0.00"
                        )
                        records.add(syntheticRecord)

                        // Persist to subcollection so subsequent loads have an official historical document
                        try {
                            val recordData = hashMapOf(
                                "timestamp" to syntheticRecord.timestamp,
                                "durationMillis" to syntheticRecord.durationMillis,
                                "planName" to syntheticRecord.planName,
                                "status" to syntheticRecord.status,
                                "amount" to syntheticRecord.amount,
                                "created_at" to syntheticRecord.timestamp
                            )
                            db.collection("users").document(targetUid)
                                .collection("subscription_history")
                                .add(recordData)
                                .await()
                        } catch (_: Exception) {}
                    }
                }
            } catch (e: Exception) {
                Log.w(TAG, "Error checking user document fallback for active subscription", e)
            }
        }

        return records.sortedByDescending { it.timestamp }
    }

    suspend fun addRecord(durationMillis: Long, planName: String, status: String, amount: String) {
        val user = AuthManager.getAuth()?.currentUser ?: FirebaseAuth.getInstance().currentUser ?: return
        addRecordForUser(user.uid, durationMillis, planName, status, amount)
    }

    suspend fun addRecordForUser(uid: String, durationMillis: Long, planName: String, status: String, amount: String) {
        val db = FirebaseFirestore.getInstance()
        val now = System.currentTimeMillis()
        val recordData = hashMapOf(
            "timestamp" to now,
            "durationMillis" to durationMillis,
            "planName" to planName,
            "status" to status,
            "amount" to amount,
            "created_at" to now
        )
        try {
            db.collection("users").document(uid)
                .collection("subscription_history")
                .add(recordData)
                .await()
            Log.d(TAG, "Subscription record created successfully for $uid: $planName")
        } catch (e: Exception) {
            Log.e(TAG, "Failed to add subscription record for $uid", e)
        }
    }
}

