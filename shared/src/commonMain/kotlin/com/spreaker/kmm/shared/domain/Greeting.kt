package com.spreaker.kmm.shared.domain

import com.spreaker.kmm.shared.data.Platform


class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}
