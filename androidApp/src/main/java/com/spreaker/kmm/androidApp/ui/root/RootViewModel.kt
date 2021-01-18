package com.spreaker.kmm.androidApp.ui.root

import android.content.Context
import android.content.Intent
import com.spreaker.kmm.androidApp.ui.recorder.RecorderActivity
import com.spreaker.kmm.androidApp.ui.room.RoomActivity

class RootViewModel(private val context: Context) {

    fun openRoom() {
        context.startActivity(Intent(context, RoomActivity::class.java))
    }

    fun openRecorder() {
        context.startActivity(Intent(context, RecorderActivity::class.java))
    }
}