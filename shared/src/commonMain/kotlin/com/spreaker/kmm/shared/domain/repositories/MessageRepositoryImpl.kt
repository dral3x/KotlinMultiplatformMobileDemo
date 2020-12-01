package com.spreaker.kmm.shared.domain.repositories

import com.badoo.reaktive.single.Single
import com.badoo.reaktive.single.single
import com.spreaker.kmm.shared.domain.api.ApiPager
import com.spreaker.kmm.shared.domain.api.ApiResponse
import com.spreaker.kmm.shared.domain.models.Message
import com.spreaker.kmm.shared.domain.repositories.MessageRepository
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json


class MessageRepositoryImpl(private val client: HttpClient): MessageRepository {

    override suspend fun getMessagesInRoom(roomId: Int): List<Message> {

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

            return apiResponse.response.items
        } else {
            // Parse error
            error("Not good!")
        }
    }

    override fun getMessagesInRoomFlow(roomId: Int): Flow<List<Message>> = flow {

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

    override fun getMessagesInRoomRx(roomId: Int): Single<List<Message>> = single { emitter ->
        runBlocking {
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

                emitter.onSuccess(apiResponse.response.items)
            } else {
                // Parse error
                emitter.onError(IllegalStateException("Not good!"))
            }
        }
    }
}
