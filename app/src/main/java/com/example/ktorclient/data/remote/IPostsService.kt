package com.example.ktorclient.data.remote

import com.example.ktorclient.data.remote.dto.PostRequest
import com.example.ktorclient.data.remote.dto.PostResponse
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*

interface IPostsService {

    suspend fun getPosts(): List<PostResponse>

    suspend fun createPost(postRequest: PostRequest): PostResponse?

    companion object {
        fun create(): IPostsService {
            return PostServiceImpl(
                client = HttpClient(Android) {
                    install(Logging) {
                        level = LogLevel.ALL
                    }
                    install(JsonFeature) {
                        serializer = KotlinxSerializer()
                    }
                }
            )
        }
    }
}