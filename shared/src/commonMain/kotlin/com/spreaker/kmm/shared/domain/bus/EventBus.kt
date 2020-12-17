package com.spreaker.kmm.shared.domain.bus

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class EventBus {
    private var queues = mutableMapOf<String, MutableSharedFlow<Any>>()

    // Returns the flow instance that will receive all events of type T
    fun <T: Any> queue(queue: EventQueue<T>): Flow<T> {
        return getFlow(queue)
    }

    // Send a new event to the correct flow for passed queue
    fun <T: Any> publish(queue: EventQueue<T>, event: T) {
        getFlow(queue).tryEmit(event)
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T: Any> getFlow(queue: EventQueue<T>): MutableSharedFlow<T> {
        val key = "${queue.id}"
        if (queues.containsKey(key)) {
            return queues[key] as MutableSharedFlow<T>
        }

        // Flow is missing, let's create a new one
        queues[key] = MutableSharedFlow(extraBufferCapacity = 1)

        return queues[key] as MutableSharedFlow<T>
    }
}