package com.bottari.bottari.di

import com.bottari.bottari.core.data.di.DataBindings
import com.bottari.bottari.core.di.DataScope
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.DependencyGraph
import dev.zacsweers.metrox.viewmodel.ViewModelGraph

@DependencyGraph(
    scope = AppScope::class,
    additionalScopes = [DataScope::class],
    bindingContainers = [
        DataBindings::class,
    ],
)
interface AppGraph : ViewModelGraph
