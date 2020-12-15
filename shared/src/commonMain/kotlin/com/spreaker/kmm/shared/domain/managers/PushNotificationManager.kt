package com.spreaker.kmm.shared.domain.managers

import com.spreaker.kmm.shared.data.pushnotifications.PushNotificationService


class PushNotificationManager(
    private val service: PushNotificationService,
    private val userManager: UserManager
) {

    private val isEnabled: Boolean
        get() = service.isEnabled

    fun onApplicationStartup() {
        if (!isEnabled) {
            return
        }

        subscribeToTopics()
    }

    private fun subscribeTo(topic: String) {
        if (!isEnabled) {
            return
        }

        service.subscribeTo(topic)
    }

    private fun unsubscribeFrom(topic: String) {
        if (!isEnabled) {
            return
        }

        service.unsubscribeFrom(topic)
    }

    private fun subscribeToTopics() {
        subscribeTo("global")

        if (userManager.loggedUser != null) {
            subscribeTo("user_${userManager.loggedUser!!.userId}")
        }
    }

    fun handleMessage(from: String, data: Map<String, Any>, onCompletion: (Boolean)-> Unit) {
        //TODO
    }
}