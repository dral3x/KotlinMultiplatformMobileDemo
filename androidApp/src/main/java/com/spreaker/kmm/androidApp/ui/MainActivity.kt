package com.spreaker.kmm.androidApp.ui

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.spreaker.kmm.androidApp.R
import com.spreaker.kmm.shared.domain.models.Message

class MainActivity : AppCompatActivity() {

    val model: RoomViewModel by viewModels { ViewModelFactory.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Prepare UI elements
        val tv: TextView = findViewById(R.id.text_view)
        findViewById<Button>(R.id.button_view).also {
            it.setOnClickListener { model.sendMessage() }
        }

        // Hydrate
        model.text.observe(this, Observer<String> { txt ->
            // Update UI
            Log.d("MainActivity", "Got text to display")
            tv.text = txt
        })

        // Start observing changes
        model.startObserving()
    }

    override fun onDestroy() {
        model.stopObserving()

        super.onDestroy()
    }

}
