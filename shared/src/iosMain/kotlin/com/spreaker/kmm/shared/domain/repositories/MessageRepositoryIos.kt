package com.spreaker.kmm.shared.domain.repositories

import com.spreaker.kmm.shared.data.FlowWrapper
import com.spreaker.kmm.shared.data.SuspendWrapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext
import kotlin.native.concurrent.freeze

class MessageRepositoryIos(private val repository: MessageRepository) {

    val scope: CoroutineScope = object : CoroutineScope {
        override val coroutineContext: CoroutineContext
            get() = SupervisorJob() + Dispatchers.Default
    }

    init {
        freeze()
    }

    fun getMessagesInRoomSuspended(roomId: Int) = SuspendWrapper { repository.getMessagesInRoom(roomId) }

    fun getMessagesInRoomFlow(roomId: Int) = FlowWrapper(repository.getMessagesInRoomFlow(roomId))
}
