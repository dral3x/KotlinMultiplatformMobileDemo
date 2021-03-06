package com.spreaker.kmm.shared.data.http

import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*

actual class HttpClientFactory actual constructor() {

    actual fun defaultHttpClient(): HttpClient {
        return HttpClient() {
            install(JsonFeature) {
                serializer = KotlinxSerializer()
            }
            install(UserAgent) {
                agent = "ktor"
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.HEADERS
            }
        }
    }
}