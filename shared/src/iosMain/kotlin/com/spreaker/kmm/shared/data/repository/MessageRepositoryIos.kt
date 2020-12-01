package com.spreaker.kmm.shared.data.repository

import com.badoo.reaktive.single.SingleWrapper
import com.badoo.reaktive.single.wrap
import com.spreaker.kmm.shared.domain.models.Message
import com.spreaker.kmm.shared.domain.repositories.MessageRepository
import com.spreaker.kmm.shared.framework.FlowWrapper
import com.spreaker.kmm.shared.framework.SuspendWrapper
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

    fun getMessagesInRoomRx(roomId: Int): SingleWrapper<List<Message>> =
        repository.getMessagesInRoomRx(roomId).wrap()
}
