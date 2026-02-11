package com.bottari.bottari.core.network.util

import io.ktor.client.statement.HttpResponse

/**
 * HTTP 실패 응답을 상태 코드 범위별로 구분하기 위한 네트워크 예외.
 */
sealed class HttpException private constructor(
    val response: HttpResponse,
) : RuntimeException() {
    val code: Int = response.status.value

    override val message: String = response.status.description

    class Redirect(response: HttpResponse) : HttpException(response)

    class Client(response: HttpResponse) : HttpException(response)

    class Server(response: HttpResponse) : HttpException(response)

    class Unknown(response: HttpResponse) : HttpException(response)

    companion object {
        /**
         * [HttpResponse]의 상태 코드를 기반으로 적절한 [HttpException] 하위 타입을 반환한다.
         */
        fun from(response: HttpResponse): HttpException =
            when (response.status.value) {
                in 300..399 -> Redirect(response)
                in 400..499 -> Client(response)
                in 500..599 -> Server(response)
                else -> Unknown(response)
            }
    }
}
