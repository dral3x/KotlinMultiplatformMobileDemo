package com.spreaker.podstories.podStoriesKit.domain

import com.spreaker.podstories.podStoriesKit.domain.Platform


class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}
