package com.example.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError

@Composable
fun AdmobBanner(modifier: Modifier = Modifier) {
    var adFailed by remember { mutableStateOf(false) }

    if (!adFailed) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Color.Transparent),
            contentAlignment = Alignment.Center
        ) {
            AndroidView(
                modifier = Modifier.fillMaxWidth(),
                factory = { context ->
                    AdView(context).apply {
                        setAdSize(AdSize.BANNER)
                        // User's real AdUnit ID
                        adUnitId = "ca-app-pub-5124881073806951/3921171890"
                        
                        adListener = object : AdListener() {
                            override fun onAdLoaded() {
                                Log.d("AdmobBanner", "Ad loaded successfully")
                            }
                            override fun onAdFailedToLoad(adError: LoadAdError) {
                                adFailed = true
                                Log.e("AdmobBanner", "Ad failed to load: ${adError.message} (Code: ${adError.code})")
                            }
                        }
                        
                        loadAd(AdRequest.Builder().build())
                    }
                }
            )
        }
    }
}
