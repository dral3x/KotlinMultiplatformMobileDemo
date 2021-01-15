package com.spreaker.kmm.androidApp.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : AppCompatActivity() {

    private val model: RoomViewModel by viewModels { ViewModelFactory.getInstance() }

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
            MainView(model)
        }

        // Start observing changes
        model.startObserving()
    }

    override fun onDestroy() {
        model.stopObserving()

        super.onDestroy()
    }
}

// Stated component
@Composable
fun MainView(model: RoomViewModel) {
    val state = model.text.observeAsState(initial = "...")
    MainView(
        text = state.value,
        onSendClick = { model.sendMessage() }
    )
}

// Stateless component
@Composable
fun MainView(text: String, onSendClick: () -> Unit) {
    val padding = 16.dp

    Column(
        modifier = Modifier
            .padding(padding)
            .fillMaxWidth(),
        horizontalAlignment = CenterHorizontally
    ) {
        Text(text = text)
        Spacer(Modifier.preferredSize(padding))
        Button(onClick = onSendClick) {
            Text(text = "SEND")
        }
    }
}

@Preview(name = "Initial state")
@Composable
fun PreviewMainViewInitialState() {
    MainView("...", { /* ignored */ })
}

@Preview(name = "Sending state")
@Composable
fun PreviewMainViewSendingState() {
    MainView("Sending message...", { /* ignored */ })
}