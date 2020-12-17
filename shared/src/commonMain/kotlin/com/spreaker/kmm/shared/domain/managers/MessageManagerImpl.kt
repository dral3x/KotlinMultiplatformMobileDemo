package com.spreaker.kmm.shared.domain.managers

import com.spreaker.kmm.shared.PlatformLogger
import com.spreaker.kmm.shared.data.concurrent.CoroutineScopes
import com.spreaker.kmm.shared.data.concurrent.ensureMainScope
import com.spreaker.kmm.shared.data.concurrent.ensureNeverFrozen
import com.spreaker.kmm.shared.data.concurrent.freeze
import com.spreaker.kmm.shared.domain.bus.EventBus
import com.spreaker.kmm.shared.domain.bus.EventQueue
import com.spreaker.kmm.shared.domain.events.MessageSendStateChangeEvent
import com.spreaker.kmm.shared.domain.events.SendState
import com.spreaker.kmm.shared.domain.models.Message
import com.spreaker.kmm.shared.domain.performCPUIntensiveTask
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MessageManagerImpl(
    private val bus: EventBus
): MessageManager {

    private val messageSendEventQueue = EventQueue<MessageSendStateChangeEvent>()

    init {
        ensureNeverFrozen()
    }

    override fun sendMessageInRoom(message: Message, roomId: Int) {
        ensureMainScope()

        PlatformLogger().debug("MessageManagerImpl", "Start sending message ${message.messageId}")
        bus.publish(messageSendEventQueue, MessageSendStateChangeEvent(message, SendState.SENDING))

        // I need to create this helper function to avoid coroutine to freeze this instance
        backgroundSend(bus, messageSendEventQueue, message, roomId)
    }

    private fun backgroundSend(bus: EventBus, queue: EventQueue<MessageSendStateChangeEvent>, message: Message, roomId: Int) = background {
        // Will take a while
        performCPUIntensiveTask()

        // Report completion, still from "the background"
        PlatformLogger().debug("MessageManagerImpl", "Message ${message.messageId} sent!")
        bus.publish(queue, MessageSendStateChangeEvent(message, SendState.SEND_SUCCESS))
    }

    override fun observeMessageSendStateChange(): Flow<MessageSendStateChangeEvent> {
        return bus.queue(messageSendEventQueue)
    }

    // Utils

    // Execute block inside a dedicated background thread
    private fun background(block: () -> Unit) {
        CoroutineScopes.io.launch {
            block()
        }
    }
}