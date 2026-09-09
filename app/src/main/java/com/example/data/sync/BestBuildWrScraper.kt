package com.example.data.sync

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object BestBuildWrScraper {
    suspend fun syncGlobalTierList(context: Context) {
        withContext(Dispatchers.IO) {
            // Stubbed
        }
    }

    suspend fun syncAllChampionBuilds(context: Context) {
        withContext(Dispatchers.IO) {
            // Stubbed
        }
    }
}
