package com.bottari.bottari.di

import android.app.Application
import android.content.Context
import com.bottari.bottari.core.data.di.DataBindings
import com.bottari.bottari.core.datastore.di.DataStoreBindings
import com.bottari.bottari.core.di.DataScope
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.DependencyGraph
import dev.zacsweers.metro.Provides
import dev.zacsweers.metrox.android.MetroAppComponentProviders
import dev.zacsweers.metrox.viewmodel.ViewModelGraph

@DependencyGraph(
    scope = AppScope::class,
    additionalScopes = [DataScope::class],
    bindingContainers = [
        DataBindings::class,
        DataStoreBindings::class,
    ],
)
interface AppGraph : MetroAppComponentProviders, ViewModelGraph {
    @Provides
    fun provideContext(application: Application): Context = application

    @DependencyGraph.Factory
    fun interface Factory {
        fun create(
            @Provides application: Application,
        ): AppGraph
    }
}
