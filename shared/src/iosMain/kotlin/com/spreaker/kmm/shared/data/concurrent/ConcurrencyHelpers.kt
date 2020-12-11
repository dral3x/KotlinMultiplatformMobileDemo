package com.spreaker.kmm.shared.data.concurrent


import platform.Foundation.NSThread
import kotlin.native.concurrent.ensureNeverFrozen
import kotlin.native.concurrent.freeze
import kotlin.native.concurrent.isFrozen

actual fun <T> T.freeze(): T = this.freeze()

actual val <T> T.isFrozen: Boolean
    get() = this.isFrozen

actual fun Any.ensureNeverFrozen() = this.ensureNeverFrozen()

actual fun Any.ensureMainScope() {
    if (!NSThread.currentThread.isMainThread()) {
        throw IllegalStateException("Current execution is performed outside the main scope: ${NSThread.currentThread}")
    }
}