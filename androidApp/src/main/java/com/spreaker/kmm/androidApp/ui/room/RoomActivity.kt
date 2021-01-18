package com.spreaker.kmm.androidApp.ui.room

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import com.spreaker.kmm.androidApp.di.InjectionCenter

class RoomActivity : AppCompatActivity() {

    private val model: RoomViewModel by lazy { RoomViewModel(InjectionCenter) }

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Prepare UI elements
        val tv: TextView = findViewById(R.id.text_view)
        findViewById<Button>(R.id.button_view).also {
            it.setOnClickListener { model.sendMessage() }
        }

        // Hydrate and keep it up-to-date
        model.text.observe(this, Observer<String> { txt ->
            // Update UI
            Log.d("MainActivity", "Got text to display")
            tv.text = txt
        })

        // Start observing changes
        model.startObserving()
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RoomView(model)
        }

        // Start observing changes
        model.onViewCreated()
    }

    override fun onDestroy() {
        model.onViewDestroyed()

        super.onDestroy()
    }
}