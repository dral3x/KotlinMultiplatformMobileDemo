package com.spreaker.kmm.shared.domain.api

import io.ktor.client.*


expect class ApiClientBuilder() {
    fun defaultHttpClient(): HttpClient
}