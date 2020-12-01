package com.spreaker.podstories.podStoriesKit

import com.spreaker.podstories.podStoriesKit.domain.Greeting
import org.junit.Assert.assertTrue
import org.junit.Test

class AndroidGreetingTest {

    @Test
    fun testExample() {
        assertTrue("Check Android is mentioned", Greeting().greeting().contains("Android"))
    }
}