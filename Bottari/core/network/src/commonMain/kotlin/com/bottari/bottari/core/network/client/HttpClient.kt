package com.bottari.bottari.core.network.client

import com.bottari.bottari.core.network.client.plugin.installAuthHeader
import com.bottari.bottari.core.network.client.plugin.installContentNegotiation
import com.bottari.bottari.core.network.client.plugin.installHttpResponseValidatorPlugin
import com.bottari.bottari.core.network.client.plugin.installLogging
import com.bottari.bottari.core.network.util.FirebaseInstallationIdProvider
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngineFactory

expect val httpClientEngineFactory: HttpClientEngineFactory<*>

internal fun createHttpClient(
    fidProvider: FirebaseInstallationIdProvider,
): HttpClient =
    HttpClient(httpClientEngineFactory) {
        installLogging()
        installContentNegotiation()
        installAuthHeader(fidProvider)
        installHttpResponseValidatorPlugin()
    }
