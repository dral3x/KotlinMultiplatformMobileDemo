package com.spreaker.podstories.podStoriesKit.data.repositories

import com.spreaker.podstories.podStoriesKit.domain.api.ApiPager
import com.spreaker.podstories.podStoriesKit.domain.api.ApiResponse
import com.spreaker.podstories.podStoriesKit.domain.models.Message
import com.spreaker.podstories.podStoriesKit.domain.repositories.MessageRepository
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.*
import kotlinx.serialization.json.*


class MessageRepositoryImpl(private val client: HttpClient): MessageRepository {

    override suspend fun getMessagesInRoom(roomId: Int): Flow<List<Message>> = flow {
        val response = client.request<HttpResponse> {
            url {
                method = HttpMethod.Get
                protocol = URLProtocol.HTTPS
                host = "api.spreaker.com"
                encodedPath = "/v2/episodes/$roomId/messages"
            }
            headers {
                append("User-Agent", "Spreaker Test App KMM")
            }
        }

        if (response.status == HttpStatusCode.OK) {
            // Parse success
            val h = response.headers

            val apiResponse = Json { ignoreUnknownKeys = true }
                .decodeFromString<ApiResponse<ApiPager<Message>>>(response.readText())

            emit(apiResponse.response.items)
        } else {
            // Parse error
            error("Not good!")
        }
    }
}