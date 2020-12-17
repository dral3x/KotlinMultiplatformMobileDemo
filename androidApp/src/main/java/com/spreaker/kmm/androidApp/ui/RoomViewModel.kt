package com.spreaker.kmm.androidApp.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spreaker.kmm.androidApp.di.InjectionCenter
import com.spreaker.kmm.shared.data.concurrent.ensureMainScope
import com.spreaker.kmm.shared.domain.Greeting
import com.spreaker.kmm.shared.domain.events.SendState
import com.spreaker.kmm.shared.domain.managers.MessageManager
import com.spreaker.kmm.shared.domain.models.Message
import com.spreaker.kmm.shared.domain.repositories.MessageRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class RoomViewModel(
    private val repository: MessageRepository,
    private val manager: MessageManager
): ViewModel() {

    // Convenience constructor that takes dependencies from the current InjectorCenter
    constructor(injector: InjectionCenter) : this(
            repository = injector.inject(MessageRepository::class.java),
            manager = injector.inject(MessageManager::class.java)
    )

    // List of disposables (like in Rx)
    var jobs = mutableListOf<Job>()

    private val _text = MutableLiveData<String>(Greeting().greeting())
    val text: LiveData<String>
        get() = _text

    fun startObserving() {
        // Always from main thread
        Log.d("RoomViewModel", "startObserving")

        /*
        // Option 1: use suspended function
        viewModelScope.launch(Dispatchers.Main) {
            val result = repository.getMessagesInRoom(18631166)
            messages.value = result
            Log.i("RoomViewModel", result.toString())
        }
        */

        // Option 2: use (like iOS) the Flow
        viewModelScope.launch { // Now on main thread
            repository.getMessagesInRoomFlow(18631166)
                .collect {
                    _text.value = it[0].text
                }
        }.let {
            // Collect current job so we can cancel it on demand
            jobs.add(it)
        }

        // Observe changes
        viewModelScope.launch {
            manager.observeMessageSendStateChange()
                .collect {
                    when (it.state) {
                        SendState.SENDING -> Log.d(
                            "RoomViewModel",
                            "Sending message ${it.message.messageId}"
                        )
                        SendState.SEND_SUCCESS -> Log.d(
                            "RoomViewModel",
                            "Message ${it.message.messageId} sent"
                        )
                        SendState.SEND_SUCCESS -> Log.d(
                            "RoomViewModel",
                            "Message ${it.message.messageId} NOT sent"
                        )
                    }
                }
        }.let { jobs.add(it) }
    }

    fun stopObserving() {
        Log.d("RoomViewModel", "stopObserving")

        jobs.forEach { it.cancel() }
        jobs.clear()
    }

    fun sendMessage() {
        // Always from main thread
        manager.sendMessageInRoom(
                Message(messageId = 1, authorId = 42, authorFullname = "Sandro", text = "Ciao!"),
                18631166)
    }
}