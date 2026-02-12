package com.bottari.bottari.core.network.util

import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse

/**
 * API 호출 성공 시 응답 본문을 [T]로 역직렬화해 반환한다.
 *
 * 실패(HTTP/네트워크/파싱) 시 [Result.failure]를 반환한다.
 */
internal suspend inline fun <reified T> request(
    crossinline block: suspend () -> HttpResponse,
): Result<T> =
    requestResponse { block() }
        .mapCatching { response -> response.body<T>() }

/**
 * API 호출의 원본 [HttpResponse]를 그대로 반환한다.
 */
internal suspend inline fun requestResponse(
    block: suspend () -> HttpResponse,
): Result<HttpResponse> = runCatching { block() }
