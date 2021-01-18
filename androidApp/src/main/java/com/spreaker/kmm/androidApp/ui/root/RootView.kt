package com.spreaker.kmm.androidApp.ui.root

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun RootView(model: RootViewModel) {
    RootView(
        openRoomAction = { model.openRoom() },
        openRecorderAction = { model.openRecorder() }
    )
}

@Composable
fun RootView(
    openRoomAction: () -> Unit,
    openRecorderAction: () -> Unit
) {
    val padding = 16.dp
    Column(
        modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(padding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OpenScreenButton(text = "Room", onClick = openRoomAction)
        Spacer(modifier = Modifier.preferredSize(padding))
        OpenScreenButton(text = "Recorder", onClick = openRecorderAction)
    }
}

@Composable
fun OpenScreenButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick
    ) {
        Text(text)
    }
}

@Preview
@Composable
fun PreviewRootView() {
    RootView({ /* ignored */ }, { /* ignored */ })
}

