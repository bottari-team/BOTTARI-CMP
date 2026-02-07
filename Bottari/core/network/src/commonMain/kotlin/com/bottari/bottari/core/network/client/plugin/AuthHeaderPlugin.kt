package com.bottari.bottari.core.network.client.plugin

import com.bottari.bottari.core.network.FirebaseInstallationIdProvider
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.api.createClientPlugin
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header

internal fun HttpClientConfig<*>.installAuthHeader(
    fidProvider: FirebaseInstallationIdProvider,
) {
    install(AuthHeaderPlugin) {
        this.provider = fidProvider
    }
}

private val AuthHeaderPlugin =
    createClientPlugin(
        name = "AuthHeaderPlugin",
        createConfiguration = ::Config,
    ) {
        val provider = pluginConfig.provider

        onRequest { request: HttpRequestBuilder, _ ->
            val installationId = provider.getInstallationId()
            request.header("Authorization", "ssaid $installationId")
        }
    }

private class Config {
    lateinit var provider: FirebaseInstallationIdProvider
}
