package com.spreaker.podstories.androidApp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class ViewModelFactory: ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == RoomViewModel::class.java) {
            return RoomViewModel(Injection.provideRoomRepository()) as T
        }

        throw IllegalArgumentException("Unknown model class $modelClass")
    }

    companion object {
        private var INSTANCE: ViewModelFactory? = null
        fun getInstance(): ViewModelFactory {
            if (INSTANCE == null) {
                INSTANCE = ViewModelFactory()
            }
            return INSTANCE!!
        }
    }
}