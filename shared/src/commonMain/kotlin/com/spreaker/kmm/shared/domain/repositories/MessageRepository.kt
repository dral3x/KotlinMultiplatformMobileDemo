package com.spreaker.kmm.shared.domain.repositories

import com.spreaker.kmm.shared.domain.models.Message
import kotlinx.coroutines.flow.Flow

interface MessageRepository {
    @Throws(Exception::class)
    @Deprecated("Flow is a better choice for iOS")
    suspend fun getMessagesInRoom(roomId: Int): List<Message>

    fun getMessagesInRoomFlow(roomId: Int): Flow<List<Message>>
}