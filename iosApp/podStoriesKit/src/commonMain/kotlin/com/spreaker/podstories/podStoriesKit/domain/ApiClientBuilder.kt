package com.spreaker.podstories.podStoriesKit.domain

import io.ktor.client.*


expect class ApiClientBuilder() {
    fun defaultHttpClient(): HttpClient
}