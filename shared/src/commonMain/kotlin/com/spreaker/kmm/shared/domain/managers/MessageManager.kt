package com.spreaker.kmm.shared.domain.managers

import com.spreaker.kmm.shared.domain.models.Message


interface MessageManager {
    fun sendMessageInRoom(message: Message, roomId: Int)
}