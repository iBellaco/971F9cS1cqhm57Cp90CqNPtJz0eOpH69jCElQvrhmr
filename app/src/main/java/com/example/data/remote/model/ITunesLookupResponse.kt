package com.example.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Modelo de respuesta raíz de la API de iTunes Lookup.
 * Endpoint: https://itunes.apple.com/lookup?bundleId=com.riotgames.league.wildrift
 */
@JsonClass(generateAdapter = true)
data class ITunesLookupResponse(
    @Json(name = "resultCount")
    val resultCount: Int,
    @Json(name = "results")
    val results: List<ITunesAppResult> = emptyList()
)

/**
 * Representa el resultado individual de la aplicación devuelta por iTunes.
 */
@JsonClass(generateAdapter = true)
data class ITunesAppResult(
    @Json(name = "bundleId")
    val bundleId: String? = null,
    @Json(name = "trackName")
    val trackName: String? = null,
    @Json(name = "version")
    val version: String? = null,
    @Json(name = "currentVersionReleaseDate")
    val currentVersionReleaseDate: String? = null,
    @Json(name = "minimumOsVersion")
    val minimumOsVersion: String? = null,
    @Json(name = "releaseNotes")
    val releaseNotes: String? = null
)
