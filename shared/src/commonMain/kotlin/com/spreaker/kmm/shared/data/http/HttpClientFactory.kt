package com.spreaker.kmm.shared.data.http

import io.ktor.client.*

expect class HttpClientFactory() {
    // Each platform will return a custom initialized HttpClient
    fun defaultHttpClient(): HttpClient
}