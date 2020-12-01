package com.spreaker.podstories.podStoriesKit.domain.api

import kotlinx.serialization.Serializable


@Serializable
class ApiResponse<T>(
    val response: T
)