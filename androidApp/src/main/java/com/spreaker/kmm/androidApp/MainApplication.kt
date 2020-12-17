package com.spreaker.kmm.androidApp

import android.app.Application
import com.spreaker.kmm.androidApp.data.pushnotifications.PushNotificationServiceAndroidImpl
import com.spreaker.kmm.androidApp.di.InjectionCenter
import com.spreaker.kmm.shared.data.http.HttpClientFactory
import com.spreaker.kmm.shared.data.pushnotifications.PushNotificationService
import com.spreaker.kmm.shared.domain.bus.EventBus
import com.spreaker.kmm.shared.domain.managers.*
import com.spreaker.kmm.shared.domain.models.User
import com.spreaker.kmm.shared.domain.repositories.MessageRepository
import com.spreaker.kmm.shared.domain.repositories.MessageRepositoryImpl
import io.ktor.client.*


class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        // Build everything
        val userManager = UserManager()
        InjectionCenter.put(userManager)

        val httpClient = HttpClientFactory().defaultHttpClient()
        InjectionCenter.put(httpClient, HttpClient::class.java)

        val bus = EventBus()
        InjectionCenter.put(bus)

        InjectionCenter.put(MessageRepositoryImpl(httpClient), MessageRepository::class.java)
        InjectionCenter.put(MessageManagerImpl(bus), MessageManager::class.java)

        val pushNotificationService = PushNotificationServiceAndroidImpl()
        val pushNotificationManager = PushNotificationManager(pushNotificationService, userManager)
        InjectionCenter.put(pushNotificationService, PushNotificationService::class.java)
        InjectionCenter.put(pushNotificationManager)

        // Fake signed-in user
        InjectionCenter.inject(UserManager::class.java).loggedUser = User(42, "Chuck Norris")

        InjectionCenter.inject(PushNotificationManager::class.java).onApplicationStartup()
    }
}