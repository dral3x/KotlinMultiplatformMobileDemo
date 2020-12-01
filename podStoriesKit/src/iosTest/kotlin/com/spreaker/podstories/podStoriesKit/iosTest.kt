package com.spreaker.podstories.podStoriesKit

import com.spreaker.podstories.podStoriesKit.domain.Greeting
import kotlin.test.Test
import kotlin.test.assertTrue

class IosGreetingTest {

    @Test
    fun testExample() {
        assertTrue(Greeting().greeting().contains("iOS"), "Check iOS is mentioned")
    }
}