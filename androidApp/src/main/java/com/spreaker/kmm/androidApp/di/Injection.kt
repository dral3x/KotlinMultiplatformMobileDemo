package com.spreaker.kmm.androidApp.di

import com.spreaker.kmm.shared.data.repositories.MessageRepositoryImpl
import com.spreaker.kmm.shared.domain.api.ApiClientBuilder
import com.spreaker.kmm.shared.domain.repositories.MessageRepository


object Injection {

    fun provideMessageRepository(): MessageRepository {
        return MessageRepositoryImpl(ApiClientBuilder().defaultHttpClient())
    }
}