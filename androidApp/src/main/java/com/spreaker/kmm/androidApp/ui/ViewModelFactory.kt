package com.spreaker.kmm.androidApp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.spreaker.kmm.androidApp.di.InjectionCenter
import com.spreaker.kmm.shared.domain.managers.MessageManager
import com.spreaker.kmm.shared.domain.repositories.MessageRepository


class ViewModelFactory: ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        // Here's how we build new instances of ViewModel classes
        when (modelClass) {
            RoomViewModel::class.java -> return RoomViewModel(InjectionCenter) as T
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