package com.spreaker.podstories.podStoriesKit.domain.api

import io.ktor.client.*


expect class ApiClientBuilder() {
    fun defaultHttpClient(): HttpClient
}