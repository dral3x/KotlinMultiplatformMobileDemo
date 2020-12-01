package com.spreaker.kmm.shared.data.http

import io.ktor.client.*

expect class HttpClientFactory() {
    fun defaultHttpClient(): HttpClient
}