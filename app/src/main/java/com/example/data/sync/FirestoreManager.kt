package com.example.data.sync

import android.util.Log
import com.example.data.WildRiftRepository
import com.example.model.Champion
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FirestoreManager {
    private val db = FirebaseFirestore.getInstance()
    private val championsCollection = db.collection("champions")

    suspend fun uploadLocalDataToFirestore(): Boolean {
        return try {
            val localChampions = WildRiftRepository.champions
            for (champion in localChampions) {
                championsCollection.document(champion.id).set(champion).await()
                Log.d("FirestoreManager", "Uploaded champion: ${champion.name}")
            }
            true
        } catch (e: Exception) {
            Log.e("FirestoreManager", "Error uploading data", e)
            false
        }
    }

    suspend fun getChampionsFromFirestore(): List<Champion> {
        return try {
            val snapshot = championsCollection.get().await()
            val champions = snapshot.documents.mapNotNull { doc ->
                try {
                    doc.toObject(Champion::class.java)
                } catch (e: Exception) {
                    Log.e("FirestoreManager", "Error parsing champion ${doc.id}", e)
                    null
                }
            }
            champions
        } catch (e: Exception) {
            Log.e("FirestoreManager", "Error fetching champions", e)
            emptyList()
        }
    }
}
