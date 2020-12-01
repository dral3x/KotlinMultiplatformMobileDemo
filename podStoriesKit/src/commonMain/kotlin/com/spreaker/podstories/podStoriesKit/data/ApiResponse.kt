package com.spreaker.podstories.podStoriesKit.data

import kotlinx.serialization.Serializable


@Serializable
class ApiResponse<T>(
    val response: T?
)