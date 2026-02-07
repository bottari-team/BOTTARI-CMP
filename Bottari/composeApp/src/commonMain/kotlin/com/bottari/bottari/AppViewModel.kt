package com.bottari.bottari

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bottari.bottari.core.di.ViewModelKey
import com.bottari.bottari.core.domain.usecase.ObservePermissionFlagUseCase
import com.bottari.bottari.core.domain.usecase.SavePermissionFlagUseCase
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesIntoMap
import dev.zacsweers.metro.Inject
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@Inject
@ViewModelKey(AppViewModel::class)
@ContributesIntoMap(AppScope::class)
class AppViewModel(
    private val observePermissionFlag: ObservePermissionFlagUseCase,
    private val savePermissionFlag: SavePermissionFlagUseCase,
) : ViewModel() {
    val permissionFlag: StateFlow<Boolean> =
        observePermissionFlag()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = false,
            )

    fun updatePermissionFlag() {
        viewModelScope.launch {
            savePermissionFlag(permissionFlag.value.not())
                .onSuccess { Napier.d { "Permission flag saved" } }
                .onFailure { Napier.e(it) { "Failed to save permission flag" } }
        }
    }
}
