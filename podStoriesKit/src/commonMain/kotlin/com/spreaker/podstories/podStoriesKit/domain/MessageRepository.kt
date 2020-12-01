package com.spreaker.podstories.podStoriesKit.domain

import kotlinx.coroutines.flow.Flow

interface MessageRepository {
    fun getMessagesInRoom(roomId: Int): Flow<List<Message>>
}