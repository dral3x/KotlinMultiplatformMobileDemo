package com.spreaker.kmm.shared


internal expect class PlatformLogger() {
    fun debug(tag: String, message: String)
    fun warn(tag: String, message: String)
    fun error(tag: String, message: String)
}