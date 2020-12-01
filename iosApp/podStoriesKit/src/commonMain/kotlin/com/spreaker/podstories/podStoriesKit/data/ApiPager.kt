package com.spreaker.podstories.podStoriesKit.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiPager<T>(
    val items: List<T>,
    @SerialName("next_url") val nextUrl: String?
)