package com.spreaker.podstories.androidApp.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spreaker.podstories.podStoriesKit.domain.api.ApiClient
import com.spreaker.podstories.podStoriesKit.domain.models.Message
import com.spreaker.podstories.podStoriesKit.data.repositories.MessageRepositoryImpl
import com.spreaker.podstories.podStoriesKit.domain.repositories.MessageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.singleOrNull
import kotlinx.coroutines.launch


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

        viewModelScope.launch(Dispatchers.Main) {
            val result = repository.getMessagesInRoomFlow(18631166).singleOrNull()
            messages.value = result
            Log.i("RoomViewModel", result.toString())
        }
    }
}