package com.bottari.bottari.core.data.repository

import com.bottari.bottari.core.domain.repository.MemberRepository
import com.bottari.bottari.core.model.member.Nickname
import com.bottari.bottari.core.model.member.RegisteredMember
import com.bottari.bottari.core.network.datasource.MemberRemoteDataSource
import com.bottari.bottari.core.network.model.member.MemberNicknameSaveRequest
import com.bottari.bottari.core.network.model.member.MemberRegisterRequest
import com.bottari.bottari.core.network.util.FcmTokenProvider
import com.bottari.bottari.core.network.util.FirebaseInstallationIdProvider
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.SingleIn

@Inject
@SingleIn(AppScope::class)
class DefaultMemberRepository(
    private val dataSource: MemberRemoteDataSource,
    private val fidProvider: FirebaseInstallationIdProvider,
    private val fcmTokenProvider: FcmTokenProvider,
) : MemberRepository {
    override suspend fun registerMember(): Result<Long> =
        runCatching {
            val fid = fidProvider.getInstallationId()
            val fcmToken = fcmTokenProvider.getToken()
            MemberRegisterRequest(ssaid = fid, fcmToken = fcmToken)
        }.mapCatching { request ->
            dataSource.registerMember(request).getOrThrow()
        }

    override suspend fun saveMemberNickname(nickname: Nickname): Result<Unit> =
        dataSource.saveMemberNickname(MemberNicknameSaveRequest.fromDomain(nickname))

    override suspend fun checkRegisteredMember(): Result<RegisteredMember> =
        dataSource.checkRegisteredMember()
            .map { response -> response.toDomain() }
}
