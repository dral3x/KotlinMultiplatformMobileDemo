package com.spreaker.kmm.androidApp.ui.recorder

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RecorderView() {
    val padding = 16.dp

    Column(
        modifier = Modifier.padding(padding).fillMaxWidth().fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "I'm a recorder")
    }
}

@Preview
@Composable
fun PreviewRecorderView() {
    RecorderView()
}