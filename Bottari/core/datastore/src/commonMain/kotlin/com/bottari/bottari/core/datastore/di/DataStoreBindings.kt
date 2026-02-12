package com.bottari.bottari.core.datastore.di

import com.bottari.bottari.core.datastore.datasource.AppConfigDataSource
import com.bottari.bottari.core.datastore.datasource.DefaultAppConfigDataSource
import com.bottari.bottari.core.datastore.datasource.DefaultTooltipDataSource
import com.bottari.bottari.core.datastore.datasource.TooltipDataSource
import dev.zacsweers.metro.BindingContainer
import dev.zacsweers.metro.Binds

@BindingContainer
interface DataStoreBindings {
    @Binds
    val DefaultAppConfigDataSource.bind: AppConfigDataSource

    @Binds
    val DefaultTooltipDataSource.bind: TooltipDataSource
}
