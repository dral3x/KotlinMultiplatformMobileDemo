package com.spreaker.podstories.podStoriesKit.data.repository

import com.spreaker.podstories.podStoriesKit.domain.repositories.MessageRepository
import com.spreaker.podstories.podStoriesKit.framework.FlowWrapper
import com.spreaker.podstories.podStoriesKit.framework.SuspendWrapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext
import kotlin.native.concurrent.freeze


class MessageRepositoryIos(private val repository: MessageRepository) {

    val scope: CoroutineScope = object : CoroutineScope {
        override val coroutineContext: CoroutineContext
            get() = SupervisorJob() + Dispatchers.Main
    }

    init {
        freeze()
    }

    fun getMessagesInRoomSuspended(roomId: Int) = SuspendWrapper { repository.getMessagesInRoom(roomId) }

    fun getMessagesInRoomFlow(roomId: Int) = FlowWrapper(repository.getMessagesInRoomFlow(roomId))
}
