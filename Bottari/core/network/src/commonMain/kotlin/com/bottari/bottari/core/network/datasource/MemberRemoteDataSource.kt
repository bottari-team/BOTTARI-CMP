package com.bottari.bottari.core.network.datasource

import com.bottari.bottari.core.network.model.member.MemberNicknameSaveRequest
import com.bottari.bottari.core.network.model.member.MemberRegisterCheckResponse
import com.bottari.bottari.core.network.model.member.MemberRegisterRequest

interface MemberRemoteDataSource {
    suspend fun registerMember(request: MemberRegisterRequest): Result<Long?>

    suspend fun saveMemberNickname(request: MemberNicknameSaveRequest): Result<Unit>

    suspend fun checkRegisteredMember(): Result<MemberRegisterCheckResponse>
}
