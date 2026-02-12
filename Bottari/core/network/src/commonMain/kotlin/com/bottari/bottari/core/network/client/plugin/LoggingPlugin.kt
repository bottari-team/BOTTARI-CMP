package com.bottari.bottari.core.network.client.plugin

import com.bottari.bottari.core.network.BuildKonfig
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement

internal fun HttpClientConfig<*>.installLogging() {
    install(Logging) {
        level = if (BuildKonfig.IS_DEBUG) LogLevel.ALL else LogLevel.NONE
        logger = PrettyLogger
    }
}

@OptIn(ExperimentalSerializationApi::class)
private object PrettyLogger : Logger {
    private const val TAG: String = "HTTP-LOG"

    private const val BODY_START_TOKEN: String = "BODY START"
    private const val BODY_END_TOKEN: String = "BODY END"

    private val SEPARATOR: String = "----------".repeat(10)

    private val json =
        Json {
            prettyPrint = true
        }

    override fun log(message: String) {
        val prettyMessage = message.prettyBodyIfExists()
        Napier.v(tag = TAG, message = prettyMessage)
    }

    private fun String.prettyBodyIfExists(): String {
        val range = bodyRangeOrNull() ?: return this

        val rawBody = substring(range).trim()
        val prettyBody = rawBody.prettyJsonOrSelf()

        return replaceRange(range, "\n$prettyBody\n")
            .wrapWithSeparator()
    }

    private fun String.bodyRangeOrNull(): IntRange? {
        val startIndex = indexOf(BODY_START_TOKEN)
        if (startIndex == -1) return null

        val endIndex = indexOf(BODY_END_TOKEN)
        if (endIndex == -1) return null

        val bodyStartIndex = startIndex + BODY_START_TOKEN.length
        if (bodyStartIndex >= endIndex) return null

        return bodyStartIndex until endIndex
    }

    private fun String.prettyJsonOrSelf(): String {
        if (isBlank()) return this

        return runCatching {
            val parsed = json.parseToJsonElement(this)
            json.encodeToString(JsonElement.serializer(), parsed)
        }.getOrElse { e ->
            Napier.e(tag = TAG, throwable = e, message = e.message.orEmpty())
            this
        }
    }

    private fun String.wrapWithSeparator(): String {
        return buildString {
            appendLine(SEPARATOR)
            append(this@wrapWithSeparator)
            appendLine()
            appendLine(SEPARATOR)
        }
    }
}
