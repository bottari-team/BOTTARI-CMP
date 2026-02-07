package com.bottari.bottari.core.data.di

import com.bottari.bottari.core.data.repository.DefaultAppConfigRepository
import com.bottari.bottari.core.domain.repository.AppConfigRepository
import dev.zacsweers.metro.BindingContainer
import dev.zacsweers.metro.Binds

@BindingContainer
interface DataBindings {
    @Binds
    val DefaultAppConfigRepository.bind: AppConfigRepository
}
