package com.spreaker.kmm.androidApp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import com.spreaker.kmm.androidApp.di.InjectionCenter
import com.spreaker.kmm.androidApp.ui.room.RoomViewModel
import com.spreaker.kmm.androidApp.ui.root.RootView
import com.spreaker.kmm.androidApp.ui.root.RootViewModel

class MainActivity : AppCompatActivity() {

    private val model: RootViewModel by lazy { RootViewModel(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RootView(model)
        }
    }
}