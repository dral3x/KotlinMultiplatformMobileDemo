package com.spreaker.kmm.androidApp.ui.recorder

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.io.IOException

private const val LOG_TAG = "RecorderViewModel"
private const val REQUEST_RECORD_AUDIO_PERMISSION = 200

class RecorderViewModel(private val activity: Activity) {

    private val _permissionsState = MutableLiveData<String>("Unknown")
    val permissionsState: LiveData<String>
        get() = _permissionsState

    private val _recorderState = MutableLiveData<String>("Idle")
    val  recorderState: LiveData<String>
        get() = _recorderState

    private var fileName: String = ""
    private var recorder: MediaRecorder? = null
    private var player: MediaPlayer? = null

    fun onViewCreated() {
        // Check permission current status
        val accepted = ContextCompat.checkSelfPermission(activity, Manifest.permission.RECORD_AUDIO)
        _permissionsState.value = if (isPermissionRequestAccepted(accepted)) "Granted" else "Denied"
    }

    fun onViewDestroyed() {
        // Nothing to do
    }

    fun requestPermissions() {
        val permissions = arrayOf(Manifest.permission.RECORD_AUDIO)
        ActivityCompat.requestPermissions(activity, permissions, REQUEST_RECORD_AUDIO_PERMISSION)
    }

    fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        val accepted = if (requestCode == REQUEST_RECORD_AUDIO_PERMISSION) {
            isPermissionRequestAccepted(grantResults[0])
        } else {
            false
        }
        _permissionsState.value = if (accepted) "Granted" else "Denied"
    }

    fun startRecording() {

        // Record to the external cache directory for visibility
        fileName = "${activity.externalCacheDir!!.absolutePath}/audio.3gp"
        Log.i(LOG_TAG, "Recording to file ${fileName}")

        recorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setOutputFile(fileName)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)

            try {
                prepare()
            } catch (e: IOException) {
                Log.e(LOG_TAG, "prepare() failed")
            }

            start()
        }

        _recorderState.value = "Recording..."
    }

    fun stopRecording() {
        recorder?.apply {
            stop()
            release()
        }
        recorder = null

        _recorderState.value = "Idle"
    }

    fun replayRecording() {
        player = MediaPlayer().apply {
            try {
                setDataSource(fileName)
                prepare()
                start()
            } catch (e: IOException) {
                Log.e(LOG_TAG, "prepare() failed")
            }
        }
    }

    private fun stopPlaying() {
        player?.release()
        player = null
    }

    private fun isPermissionRequestAccepted(resultCode: Int): Boolean {
        return resultCode == PackageManager.PERMISSION_GRANTED
    }
}