package com.spreaker.kmm.shared.data.concurrent

import android.os.Looper


actual fun <T> T.freeze(): T = this

actual val <T> T.isFrozen: Boolean
    get() = false

actual fun Any.ensureNeverFrozen() { /* no-op */ }

actual fun Any.ensureMainScope() {
    if (Looper.myLooper() != Looper.getMainLooper()) {
        throw IllegalThreadStateException("Current execution is performed outside the main scope: ${Thread.currentThread()}")
    }
}