package com.bottari.bottari.core.network.datasource

import com.bottari.bottari.core.network.model.member.MemberNicknameSaveRequest
import com.bottari.bottari.core.network.model.member.MemberRegisterCheckResponse
import com.bottari.bottari.core.network.model.member.MemberRegisterRequest
import com.bottari.bottari.core.network.service.MemberService
import com.bottari.bottari.core.network.util.extractIdFromHeader
import com.bottari.bottari.core.network.util.request
import com.bottari.bottari.core.network.util.requestResponse
import dev.zacsweers.metro.Inject

@Inject
class DefaultMemberRemoteDataSource(
    private val memberService: MemberService,
) : MemberRemoteDataSource {
    override suspend fun registerMember(request: MemberRegisterRequest): Result<Long?> =
        requestResponse {
            memberService.registerMember(request)
        }.mapCatching { response -> response.extractIdFromHeader() }

    override suspend fun saveMemberNickname(request: MemberNicknameSaveRequest): Result<Unit> =
        request { memberService.saveMemberNickname(request) }

    override suspend fun checkRegisteredMember(): Result<MemberRegisterCheckResponse> =
        request { memberService.checkRegisteredMember() }
}
