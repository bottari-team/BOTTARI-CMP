package com.bottari.bottari.core.network.util

import io.ktor.client.statement.HttpResponse

fun HttpResponse.extractIdFromHeader(): Long? =
    headers.getAll("Location")
        ?.firstOrNull()
        ?.substringAfterLast("/")
        ?.toLongOrNull()
