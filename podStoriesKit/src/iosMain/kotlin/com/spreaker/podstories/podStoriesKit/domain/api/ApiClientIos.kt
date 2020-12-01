package com.spreaker.podstories.podStoriesKit.domain.api

import com.spreaker.podstories.podStoriesKit.framework.FlowWrapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext
import kotlin.native.concurrent.freeze

class ApiClientIos(private val apiClient: ApiClient) {
    val scope: CoroutineScope = object : CoroutineScope {
        override val coroutineContext: CoroutineContext
        get() = SupervisorJob() + Dispatchers.Default
    }

    init {
        freeze()
    }

    fun getEpisodeMessagesWrapper(episodeId: Int) =
        FlowWrapper(apiClient.getEpisodeMessagesRx(episodeId))
}