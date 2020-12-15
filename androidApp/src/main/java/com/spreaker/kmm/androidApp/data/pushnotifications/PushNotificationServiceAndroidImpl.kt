package com.spreaker.kmm.androidApp.data.pushnotifications

import android.util.Log
import com.spreaker.kmm.shared.data.pushnotifications.PushNotificationService


class PushNotificationServiceAndroidImpl: PushNotificationService {
    override val isEnabled: Boolean
        get() = true
        //TODO enabled && GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) != ConnectionResult.API_UNAVAILABLE;

    override fun subscribeTo(topic: String) {
        Log.d("PushNotificationManager", "Subscribe to topic: $topic")

        //TODO FirebaseMessaging.getInstance().subscribeToTopic(topic);
    }

    override fun unsubscribeFrom(topic: String) {
        Log.d("PushNotificationManager", "Unsubscribe from topic: $topic")

        //TODO FirebaseMessaging.getInstance().unsubscribeFromTopic(topic);
    }
}