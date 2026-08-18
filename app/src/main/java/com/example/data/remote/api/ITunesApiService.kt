package com.example.data.remote.api

import com.example.data.remote.model.ITunesLookupResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

/**
 * Interfaz de Retrofit para consultar el endpoint público de iTunes Lookup.
 */
interface ITunesApiService {

    /**
     * Consulta los metadatos públicos de una app por su bundleId.
     * Ejemplo: lookup?bundleId=com.riotgames.league.wildrift
     */
    @GET("lookup")
    suspend fun lookupAppByBundleId(
        @Query("bundleId") bundleId: String = WILD_RIFT_BUNDLE_ID
    ): ITunesLookupResponse

    companion object {
        const val BASE_URL = "https://itunes.apple.com/"
        const val WILD_RIFT_BUNDLE_ID = "com.riotgames.league.wildrift"

        /**
         * Factoría para instanciar el servicio con OkHttpClient y Moshi.
         */
        fun create(): ITunesApiService {
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .build()

            val moshi = Moshi.Builder()
                .addLast(KotlinJsonAdapterFactory())
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
                .create(ITunesApiService::class.java)
        }
    }
}
