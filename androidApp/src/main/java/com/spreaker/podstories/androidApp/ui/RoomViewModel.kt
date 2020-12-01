package com.spreaker.podstories.androidApp.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spreaker.podstories.podStoriesKit.domain.ApiClient
import com.spreaker.podstories.podStoriesKit.domain.Message
import com.spreaker.podstories.podStoriesKit.domain.RoomRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.DisposableHandle
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch


class RoomViewModel(
    val repository: RoomRepository,
    val apiClient: ApiClient
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
            val response = apiClient.getEpisodeMessages(18631166)

            Log.i("RoomViewModel", response)
        }
    }
}