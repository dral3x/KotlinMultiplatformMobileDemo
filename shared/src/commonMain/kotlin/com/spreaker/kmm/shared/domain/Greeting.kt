package com.spreaker.kmm.shared.domain


class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}
