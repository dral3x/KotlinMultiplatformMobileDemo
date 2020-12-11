package com.spreaker.kmm.androidApp.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spreaker.kmm.shared.data.concurrent.ensureMainScope
import com.spreaker.kmm.shared.domain.Greeting
import com.spreaker.kmm.shared.domain.managers.MessageManager
import com.spreaker.kmm.shared.domain.models.Message
import com.spreaker.kmm.shared.domain.repositories.MessageRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class RoomViewModel(
    val repository: MessageRepository,
    val manager: MessageManager
): ViewModel() {

    var jobs = mutableListOf<Job>()

    private val _text = MutableLiveData<String>(Greeting().greeting())
    val text: LiveData<String>
        get() = _text

    fun startObserving() {
        ensureMainScope()
        Log.d("RoomViewModel", "startObserving")

        /*
        // Suspended function
        viewModelScope.launch(Dispatchers.Main) {
            val result = repository.getMessagesInRoom(18631166)
            messages.value = result
            Log.i("RoomViewModel", result.toString())
        }
        */

        // Flow
        viewModelScope.launch { // Now on main thread
            repository.getMessagesInRoomFlow(18631166)
                .collect {
                    _text.value = it[0].text
                }
        }.let { jobs.add(it) }
    }

    fun stopObserving() {
        ensureMainScope()
        Log.d("RoomViewModel", "stopObserving")

        jobs.forEach { it.cancel() }
        jobs.clear()
    }

    fun sendMessage() {
        ensureMainScope()

        // Always from main thread
        manager.sendMessageInRoom(
                Message(messageId = 1, authorId = 42, authorFullname = "Sandro", text = "Ciao!"),
                18631166)
    }
}