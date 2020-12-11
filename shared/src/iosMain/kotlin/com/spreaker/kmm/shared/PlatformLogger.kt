package com.spreaker.kmm.shared


internal actual class PlatformLogger {

    actual fun debug(tag: String, message: String) {
        print("$tag | $message\n")
    }

    actual fun warn(tag: String, message: String) {
        print("$tag | $message\n")
    }

    actual fun error(tag: String, message: String) {
        print("$tag | $message\n")
    }
}