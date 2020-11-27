package com.spreaker.podstories.androidApp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.spreaker.podstories.podStoriesKit.Message
import com.spreaker.podstories.podStoriesKit.RoomRepository


class RoomViewModel(
    val repository: RoomRepository
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
    }
}