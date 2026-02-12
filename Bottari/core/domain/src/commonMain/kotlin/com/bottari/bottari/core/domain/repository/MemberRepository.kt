package com.bottari.bottari.core.domain.repository

import com.bottari.bottari.core.model.member.Nickname
import com.bottari.bottari.core.model.member.RegisteredMember

interface MemberRepository {
    suspend fun registerMember(): Result<Long>

    suspend fun saveMemberNickname(nickname: Nickname): Result<Unit>

    suspend fun checkRegisteredMember(): Result<RegisteredMember>
}
