package com.spreaker.kmm.androidApp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.spreaker.kmm.androidApp.di.InjectionCenter
import com.spreaker.kmm.shared.domain.managers.MessageManager
import com.spreaker.kmm.shared.domain.repositories.MessageRepository


class ViewModelFactory: ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == RoomViewModel::class.java) {
            return RoomViewModel(
                InjectionCenter.inject(MessageRepository::class.java),
                InjectionCenter.inject(MessageManager::class.java),
            ) as T
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