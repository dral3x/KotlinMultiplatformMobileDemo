package com.spreaker.podstories.podStoriesKit


class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}
