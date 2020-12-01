package com.spreaker.podstories.androidApp.di

import com.spreaker.podstories.podStoriesKit.domain.ApiClient
import com.spreaker.podstories.podStoriesKit.domain.RoomRepository
import io.ktor.client.*
import io.ktor.client.engine.android.*


object Injection {

    fun provideRoomRepository(): RoomRepository {
        return RoomRepository()
    }

    fun provideApiClient(): ApiClient {
        return ApiClient()
    }
}