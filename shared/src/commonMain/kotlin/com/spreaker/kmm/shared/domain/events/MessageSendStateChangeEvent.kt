package com.spreaker.kmm.shared.domain.events

import com.spreaker.kmm.shared.domain.models.Message

enum class SendState {
    SENDING, SEND_SUCCESS, SEND_FAILURE
}

data class MessageSendStateChangeEvent(val message: Message, val state: SendState)
