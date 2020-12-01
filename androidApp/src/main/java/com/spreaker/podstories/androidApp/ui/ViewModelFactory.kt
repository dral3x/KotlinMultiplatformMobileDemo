package com.spreaker.podstories.androidApp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.spreaker.podstories.androidApp.di.Injection


class ViewModelFactory: ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == RoomViewModel::class.java) {
            return RoomViewModel(Injection.provideMessageRepository()) as T
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