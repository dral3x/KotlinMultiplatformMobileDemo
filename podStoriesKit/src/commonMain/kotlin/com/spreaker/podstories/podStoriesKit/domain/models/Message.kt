package com.spreaker.podstories.podStoriesKit.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Message(
    @SerialName("message_id") val messageId: Int,
    @SerialName("author_id") val authorId: Int?,
    @SerialName("author_fullname") val authorFullname: String?,
    val text: String
    )