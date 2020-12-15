package com.spreaker.kmm.shared.data.pushnotifications


interface PushNotificationService {
    val isEnabled: Boolean

    fun subscribeTo(topic: String)
    fun unsubscribeFrom(topic: String)
}