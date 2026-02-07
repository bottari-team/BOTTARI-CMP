package com.bottari.bottari

import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import com.bottari.bottari.di.AppGraph
import dev.zacsweers.metro.createGraph

@Suppress("FunctionNaming")
fun MainViewController() =
    ComposeUIViewController {
        val appGraph = remember { createGraph<AppGraph>() }
        App(viewModelFactory = appGraph.metroViewModelFactory)
    }
