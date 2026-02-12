package com.bottari.bottari.core.network.di

import com.bottari.bottari.core.network.datasource.DefaultMemberRemoteDataSource
import com.bottari.bottari.core.network.datasource.MemberRemoteDataSource
import dev.zacsweers.metro.BindingContainer
import dev.zacsweers.metro.Binds

@BindingContainer
interface NetworkBindings {
    @Binds
    val DefaultMemberRemoteDataSource.bind: MemberRemoteDataSource
}
