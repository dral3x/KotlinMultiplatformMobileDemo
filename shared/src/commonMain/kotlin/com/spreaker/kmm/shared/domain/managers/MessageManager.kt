package com.spreaker.kmm.shared.domain.managers

import com.spreaker.kmm.shared.domain.events.MessageSendStateChangeEvent
import com.spreaker.kmm.shared.domain.models.Message
import kotlinx.coroutines.flow.Flow


interface MessageManager {
    fun sendMessageInRoom(message: Message, roomId: Int)
    fun observeMessageSendStateChange(): Flow<MessageSendStateChangeEvent>
}