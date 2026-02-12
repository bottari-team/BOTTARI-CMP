package com.bottari.bottari.di

import com.bottari.bottari.core.data.di.DataBindings
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.DependencyGraph
import dev.zacsweers.metrox.viewmodel.ViewModelGraph

@DependencyGraph(
    scope = AppScope::class,
    bindingContainers = [
        DataBindings::class,
    ],
)
interface AppGraph : ViewModelGraph
