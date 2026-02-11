package com.bottari.bottari.core.network.service

import com.bottari.bottari.core.network.model.member.MemberNicknameSaveRequest
import com.bottari.bottari.core.network.model.member.MemberRegisterRequest
import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.PATCH
import de.jensklingenberg.ktorfit.http.POST
import io.ktor.client.statement.HttpResponse

interface MemberService {
    @POST("/members")
    suspend fun registerMember(
        @Body request: MemberRegisterRequest,
    ): HttpResponse

    @PATCH("/members/me")
    suspend fun saveMemberNickname(
        @Body request: MemberNicknameSaveRequest,
    ): HttpResponse

    @GET("/members/check")
    suspend fun checkRegisteredMember(): HttpResponse
}
