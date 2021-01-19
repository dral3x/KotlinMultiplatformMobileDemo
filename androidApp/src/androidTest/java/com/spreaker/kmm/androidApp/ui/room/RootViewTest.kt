package com.spreaker.kmm.androidApp.ui.room

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RoomViewTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    var clicked = false

    @Before
    fun setup() {
        composeTestRule.setContent {
            RoomView(text = "Random", onSendClick = { clicked = true })
        }
        clicked = false
    }

    @Test
    fun MyTest() {
        composeTestRule.onNodeWithText("Random").assertIsDisplayed()
        composeTestRule.onNodeWithText("SEND").assertIsDisplayed()
    }

    @Test
    fun testOnClick() {
        composeTestRule.onNodeWithText("SEND").performClick()
        assertTrue(clicked)
    }
}