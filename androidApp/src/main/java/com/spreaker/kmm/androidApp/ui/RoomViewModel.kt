package com.spreaker.kmm.androidApp.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.badoo.reaktive.scheduler.ioScheduler
import com.badoo.reaktive.scheduler.mainScheduler
import com.badoo.reaktive.single.observeOn
import com.badoo.reaktive.single.subscribe
import com.badoo.reaktive.single.subscribeOn
import com.spreaker.kmm.shared.domain.models.Message
import com.spreaker.kmm.shared.domain.repositories.MessageRepository


class RoomViewModel(
    val repository: MessageRepository
): ViewModel() {

    private val messages: MutableLiveData<List<Message>> by lazy {
        MutableLiveData<List<Message>>().also {
            loadMessages()
        }
    }

    fun getAllMessages(roomId: Int): LiveData<List<Message>> {
        return messages
    }

    private fun loadMessages() {
        //TODO

        /*
        // Suspended function
        viewModelScope.launch(Dispatchers.Main) {
            val result = repository.getMessagesInRoom(18631166)
            messages.value = result
            Log.i("RoomViewModel", result.toString())
        }
        */

        /*
        // Flow
        viewModelScope.launch(Dispatchers.Main) {
            val result = repository.getMessagesInRoomFlow(18631166).singleOrNull()
            messages.value = result
            Log.i("RoomViewModel", result.toString())
        }
        */

        // Rx
        repository.getMessagesInRoomRx(18631166)
            .subscribeOn(ioScheduler)
            .observeOn(mainScheduler)
            .subscribe(
                isThreadLocal = false,
                onSuccess = {
                    messages.value = it
                    Log.i("RoomViewModel", it.toString())
                },
                onError = {
                    Log.e("RoomViewModel", "Error", it)
                })
    }
}