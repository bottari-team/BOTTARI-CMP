package com.bottari.bottari

import android.app.Application
import com.bottari.bottari.di.AppGraph
import dev.zacsweers.metro.createGraphFactory
import dev.zacsweers.metrox.android.MetroAppComponentProviders
import dev.zacsweers.metrox.android.MetroApplication
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

class BottariApplication : Application(), MetroApplication {
    override val appComponentProviders: MetroAppComponentProviders by lazy {
        createGraphFactory<AppGraph.Factory>().create(this)
    }

    init {
        Napier.base(DebugAntilog())
    }
}
