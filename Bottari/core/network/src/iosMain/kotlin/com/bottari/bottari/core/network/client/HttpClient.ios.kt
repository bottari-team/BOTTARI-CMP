package com.bottari.bottari.core.network.client

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.darwin.Darwin

actual val httpClientEngineFactory: HttpClientEngineFactory<*> = Darwin
