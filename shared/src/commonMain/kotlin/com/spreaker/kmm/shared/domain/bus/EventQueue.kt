package com.spreaker.kmm.shared.domain.bus

import com.spreaker.kmm.shared.data.concurrent.freeze


// This is not safe. It MUST never be freeze
private var eventQueuesCounter = 0

public data class EventQueue<T>(val id: Int = ++eventQueuesCounter) {
    init {
        freeze()
    }
}