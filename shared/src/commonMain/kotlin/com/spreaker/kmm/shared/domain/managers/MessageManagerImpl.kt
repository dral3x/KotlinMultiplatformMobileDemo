package com.spreaker.kmm.shared.domain.managers

import com.spreaker.kmm.shared.PlatformLogger
import com.spreaker.kmm.shared.data.concurrent.ensureMainScope
import com.spreaker.kmm.shared.data.concurrent.ensureNeverFrozen
import com.spreaker.kmm.shared.domain.models.Message
import com.spreaker.kmm.shared.domain.performCPUIntensiveTask
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MessageManagerImpl: MessageManager {
    init {
        ensureNeverFrozen()
    }

    override fun sendMessageInRoom(message: Message, roomId: Int) {
        ensureMainScope()

        GlobalScope.launch {
            PlatformLogger().debug("MessageManagerImpl", "Start sending message ${message.messageId}")

            performCPUIntensiveTask()

            PlatformLogger().debug("MessageManagerImpl", "Message ${message.messageId} sent!")
        }
    }
}