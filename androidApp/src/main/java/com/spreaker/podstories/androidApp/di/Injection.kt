package com.spreaker.podstories.androidApp.di

import com.spreaker.podstories.podStoriesKit.data.repositories.MessageRepositoryImpl
import com.spreaker.podstories.podStoriesKit.domain.api.ApiClientBuilder
import com.spreaker.podstories.podStoriesKit.domain.repositories.MessageRepository


object Injection {

    fun provideMessageRepository(): MessageRepository {
        return MessageRepositoryImpl(ApiClientBuilder().defaultHttpClient())
    }
}