@file:Suppress("Indentation")

package com.bottari.bottari

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.zacsweers.metrox.viewmodel.LocalMetroViewModelFactory
import dev.zacsweers.metrox.viewmodel.MetroViewModelFactory
import dev.zacsweers.metrox.viewmodel.metroViewModel

@Composable
fun App(viewModelFactory: MetroViewModelFactory) {
    CompositionLocalProvider(
        LocalMetroViewModelFactory provides viewModelFactory,
    ) {
        MainScreen()
    }
}

@Composable
private fun MainScreen(
    viewModel: AppViewModel = metroViewModel(),
) {
    val permissionFlag by viewModel.permissionFlag.collectAsStateWithLifecycle()

    MaterialTheme {
        Scaffold(
            containerColor = Color.White,
            contentColor = Color.Black,
        ) { innerPadding ->
            Box(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                contentAlignment = Alignment.Center,
            ) {
                Button(
                    onClick = { viewModel.updatePermissionFlag() },
                ) {
                    Text(
                        text = "Permission Flag $permissionFlag",
                    )
                }
            }
        }
    }
}
