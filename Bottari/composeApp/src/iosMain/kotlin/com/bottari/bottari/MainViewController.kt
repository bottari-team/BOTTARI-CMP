package com.bottari.bottari

import androidx.compose.ui.window.ComposeUIViewController
import com.bottari.bottari.di.AppGraph
import dev.zacsweers.metro.createGraph
import platform.UIKit.UIViewController

@Suppress("FunctionNaming")
fun MainViewController(): UIViewController {
    val appGraph = createGraph<AppGraph>()

    return ComposeUIViewController {
        App(viewModelFactory = appGraph.metroViewModelFactory)
    }
}
