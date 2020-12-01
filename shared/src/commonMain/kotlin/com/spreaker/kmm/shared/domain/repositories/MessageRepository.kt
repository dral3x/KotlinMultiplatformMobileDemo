package com.spreaker.kmm.shared.domain.repositories

import com.badoo.reaktive.single.Single
import com.spreaker.kmm.shared.domain.models.Message
import kotlinx.coroutines.flow.Flow

interface MessageRepository {
    @Throws(Exception::class)
    suspend fun getMessagesInRoom(roomId: Int): List<Message>

    @Throws(Exception::class)
    fun getMessagesInRoomFlow(roomId: Int): Flow<List<Message>>

    fun getMessagesInRoomRx(roomId: Int): Single<List<Message>>
}