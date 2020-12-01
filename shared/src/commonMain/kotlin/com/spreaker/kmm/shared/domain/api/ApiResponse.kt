package com.spreaker.kmm.shared.domain.api

import kotlinx.serialization.Serializable


@Serializable
class ApiResponse<T>(
    val response: T
)