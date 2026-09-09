package com.example.data.sync

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object MetaCrawlerSyncService {
    suspend fun syncPatchData(context: Context) {
        withContext(Dispatchers.IO) {
            // Stubbed
        }
    }
}
