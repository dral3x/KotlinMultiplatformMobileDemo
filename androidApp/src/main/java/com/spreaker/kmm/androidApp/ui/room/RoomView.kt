package com.spreaker.kmm.androidApp.ui.room

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// Stated component
@Composable
fun RoomView(model: RoomViewModel) {
    val state = model.text.observeAsState(initial = "...")
    RoomView(
        text = state.value,
        onSendClick = { model.sendMessage() }
    )
}

// Stateless component
@Composable
fun RoomView(text: String, onSendClick: () -> Unit) {
    val padding = 16.dp

    Column(
        modifier = Modifier.padding(padding).fillMaxWidth().fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
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
fun PreviewRoomViewInitialState() {
    RoomView("...", { /* ignored */ })
}

@Preview(name = "Sending state")
@Composable
fun PreviewRoomViewSendingState() {
    RoomView("Sending message...", { /* ignored */ })
}