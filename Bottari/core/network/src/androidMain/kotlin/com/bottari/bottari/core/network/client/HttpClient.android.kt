package com.bottari.bottari.core.network.client

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.okhttp.OkHttp

actual val httpClientEngineFactory: HttpClientEngineFactory<*> = OkHttp
