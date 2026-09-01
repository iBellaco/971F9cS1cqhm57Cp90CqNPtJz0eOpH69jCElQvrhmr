package com.example.util

import com.example.model.SubscriptionRecord
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await

object SubscriptionHistoryManager {
    suspend fun getHistory(): List<SubscriptionRecord> {
        val user = AuthManager.getAuth()?.currentUser ?: return emptyList()
        val db = FirebaseFirestore.getInstance()
        return try {
            val snapshot = db.collection("users").document(user.uid)
                .collection("subscription_history")
                .orderBy("timestamp", Query.Direction.DESCENDING)
                .get()
                .await()
            snapshot.documents.mapNotNull { doc ->
                doc.toObject(SubscriptionRecord::class.java)?.copy(id = doc.id)
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun addRecord(durationMillis: Long, planName: String, status: String, amount: String) {
        val user = AuthManager.getAuth()?.currentUser ?: return
        addRecordForUser(user.uid, durationMillis, planName, status, amount)
    }

    suspend fun addRecordForUser(uid: String, durationMillis: Long, planName: String, status: String, amount: String) {
        val db = FirebaseFirestore.getInstance()
        val record = SubscriptionRecord(
            timestamp = System.currentTimeMillis(),
            durationMillis = durationMillis,
            planName = planName,
            status = status,
            amount = amount
        )
        try {
            db.collection("users").document(uid)
                .collection("subscription_history")
                .add(record)
                .await()
        } catch (e: Exception) {
            // Ignore for now
        }
    }
}
