package com.bottari.bottari.core.network.client.plugin

import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

internal fun HttpClientConfig<*>.installContentNegotiation() {
    expectSuccess = true

    defaultRequest {
        contentType(type = ContentType.Application.Json)
    }

    install(ContentNegotiation) {
        json(
            Json {
                isLenient = true
                ignoreUnknownKeys = true
            },
        )
    }
}
