package com.bottari.bottari.core.network.model.member

import com.bottari.bottari.core.model.member.Nickname
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MemberNicknameSaveRequest(
    @SerialName("name")
    val value: String,
) {
    companion object {
        fun fromDomain(nickname: Nickname): MemberNicknameSaveRequest = MemberNicknameSaveRequest(nickname.value)
    }
}
