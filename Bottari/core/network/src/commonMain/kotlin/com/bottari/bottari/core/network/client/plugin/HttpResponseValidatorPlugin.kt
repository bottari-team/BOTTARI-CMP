package com.bottari.bottari.core.network.client.plugin

import com.bottari.bottari.core.network.util.HttpException
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.ResponseException

internal fun HttpClientConfig<*>.installHttpResponseValidatorPlugin() {
    HttpResponseValidator {
        handleResponseException { cause ->
            val responseException = cause as? ResponseException ?: return@handleResponseException
            throw HttpException.from(responseException.response)
        }
    }
}
