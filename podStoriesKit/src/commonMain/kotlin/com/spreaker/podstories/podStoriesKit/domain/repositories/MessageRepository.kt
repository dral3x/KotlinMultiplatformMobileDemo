package com.spreaker.podstories.podStoriesKit.domain.repositories

import com.spreaker.podstories.podStoriesKit.domain.models.Message
import kotlinx.coroutines.flow.Flow

interface MessageRepository {
    @Throws(Exception::class)
    suspend fun getMessagesInRoom(roomId: Int): Flow<List<Message>>
}