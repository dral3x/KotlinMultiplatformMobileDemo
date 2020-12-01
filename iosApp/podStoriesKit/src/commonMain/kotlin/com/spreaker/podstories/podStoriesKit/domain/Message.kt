package com.spreaker.podstories.podStoriesKit.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Message(
    val messageId: Int,
    @SerialName("author_id") val authorId: Int?,
    @SerialName("author_fullname") val authorFullname: String?,
    val text: String
    )