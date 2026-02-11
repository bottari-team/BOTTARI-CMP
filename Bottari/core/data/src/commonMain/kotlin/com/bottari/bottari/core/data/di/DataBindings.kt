package com.bottari.bottari.core.data.di

import com.bottari.bottari.core.data.repository.DefaultAppConfigRepository
import com.bottari.bottari.core.data.repository.DefaultMemberRepository
import com.bottari.bottari.core.datastore.di.DataStoreBindings
import com.bottari.bottari.core.domain.repository.AppConfigRepository
import com.bottari.bottari.core.domain.repository.MemberRepository
import com.bottari.bottari.core.network.di.NetworkBindings
import dev.zacsweers.metro.BindingContainer
import dev.zacsweers.metro.Binds

@BindingContainer(
    includes = [DataStoreBindings::class, NetworkBindings::class],
)
interface DataBindings {
    @Binds
    val DefaultAppConfigRepository.bind: AppConfigRepository

    @Binds
    val DefaultMemberRepository.bind: MemberRepository
}
