package com.spreaker.kmm.shared.domain.parsers

import com.spreaker.kmm.shared.domain.api.ApiResponse
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json


inline fun <reified T> decodeApiResponse(string: String): ApiResponse<T> =
    Json { ignoreUnknownKeys = true }.decodeFromString<ApiResponse<T>>(string)