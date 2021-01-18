package com.spreaker.kmm.androidApp.ui.recorder

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RecorderView(
    permissionsState: String,
    recorderState: String,
    onRequestPermissionsClick: () -> Unit,
    onStartRecordingClick: () -> Unit,
    onStopRecordingClick: () -> Unit,
    onReplayRecordingClick: () -> Unit
) {
    val padding = 16.dp

    Column(
        modifier = Modifier
            .padding(padding)
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Permissions:", style = TextStyle(fontSize = 25.sp, fontWeight = FontWeight.Bold))
        Text(text = permissionsState)
        Button(onClick = onRequestPermissionsClick) {
            Text("Ask permissions")
        }

        Divider(modifier = Modifier.padding(padding))

        Text(text = "Recorder:", style = TextStyle(fontSize = 25.sp, fontWeight = FontWeight.Bold))
        Text(text = recorderState)
        Row {
            Button(onClick = onStartRecordingClick) {
                Text("Start")
            }
            Spacer(modifier = Modifier.width(padding))
            Button(onClick = onStopRecordingClick) {
                Text("Stop")
            }
            Spacer(modifier = Modifier.width(padding))
            Button(onClick = onReplayRecordingClick) {
                Text("Play")
            }
        }
    }
}

@Composable
fun RecorderView(model: RecorderViewModel) {
    val permissionsState = model.permissionsState.observeAsState(initial = "N/A")
    val recordingState = model.recorderState.observeAsState(initial = "N/A")
    RecorderView(
        permissionsState = permissionsState.value,
        recorderState = recordingState.value,
        onRequestPermissionsClick = { model.requestPermissions() },
        onStartRecordingClick = { model.startRecording() },
        onStopRecordingClick = { model.stopRecording() },
        onReplayRecordingClick = { model.replayRecording() }
    )
}

@Preview
@Composable
fun PreviewRecorderView() {
    RecorderView(
        permissionsState = "All Granted",
        recorderState = "Recording...",
        onRequestPermissionsClick = { /* ignored */ },
        onStartRecordingClick = { /* ignored */ },
        onStopRecordingClick = { /* ignored */ },
        onReplayRecordingClick = { /* ignored */ }
    )
}