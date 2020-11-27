package com.spreaker.podstories.androidApp

import com.spreaker.podstories.podStoriesKit.RoomRepository


object Injection {

    fun provideRoomRepository(): RoomRepository {
        return RoomRepository()
    }
}