package com.spreaker.podstories.podStoriesKit.domain.api

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

private const val BASE_URL = "api.spreaker.com"

class ApiClient() {

    private val client = ApiClientBuilder().defaultHttpClient()

    suspend fun getEpisodeMessages(episodeId: Int): String { //TODO List<Message>

        val response = client.request<HttpResponse> {
            url {
                method = HttpMethod.Get
                protocol = URLProtocol.HTTPS
                host = BASE_URL
                encodedPath = "/v2/episodes/$episodeId/messages"
            }
            headers {
                append("User-Agent", "Spreaker Test App")
            }
        }

        if (response.status == HttpStatusCode.OK) {
            // Parse success
            val h = response.headers
            return response.readText().toString()
        } else {
            // Parse error
            return "ERROR"
        }
    }

    fun getEpisodeMessagesRx(episodeId: Int): Flow<String> = flow {

        val response = client.request<HttpResponse> {
            url {
                method = HttpMethod.Get
                protocol = URLProtocol.HTTPS
                host = BASE_URL
                encodedPath = "/v2/episodes/$episodeId/messages"
            }
            headers {
                append("User-Agent", "Spreaker Test App")
            }
        }

        if (response.status == HttpStatusCode.OK) {
            // Parse success
            val h = response.headers
            emit(response.readText())
        } else {
            // Parse error
            error("Not good!")
        }
    }
}