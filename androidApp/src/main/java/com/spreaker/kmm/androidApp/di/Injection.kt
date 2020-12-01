package com.spreaker.kmm.androidApp.di

import com.spreaker.kmm.shared.domain.repositories.MessageRepositoryImpl
import com.spreaker.kmm.shared.data.http.HttpClientFactory
import com.spreaker.kmm.shared.domain.repositories.MessageRepository


object Injection {

    fun provideMessageRepository(): MessageRepository {
        return MessageRepositoryImpl(HttpClientFactory().defaultHttpClient())
    }
}