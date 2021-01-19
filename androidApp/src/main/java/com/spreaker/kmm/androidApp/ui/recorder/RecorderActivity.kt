package com.spreaker.kmm.androidApp.ui.recorder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent

class RecorderActivity: AppCompatActivity() {

    private val model: RecorderViewModel by lazy { RecorderViewModel(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecorderView(model)
        }

        model.onViewCreated()
    }

    override fun onDestroy() {
        model.onViewDestroyed()
        super.onDestroy()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        model.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}