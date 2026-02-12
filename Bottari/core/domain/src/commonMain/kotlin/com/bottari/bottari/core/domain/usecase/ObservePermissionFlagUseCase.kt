package com.bottari.bottari.core.domain.usecase

import com.bottari.bottari.core.domain.repository.AppConfigRepository
import dev.zacsweers.metro.Inject
import kotlinx.coroutines.flow.Flow

@Inject
class ObservePermissionFlagUseCase(
    private val repository: AppConfigRepository,
) {
    operator fun invoke(): Flow<Boolean> = repository.getPermissionFlag()
}
