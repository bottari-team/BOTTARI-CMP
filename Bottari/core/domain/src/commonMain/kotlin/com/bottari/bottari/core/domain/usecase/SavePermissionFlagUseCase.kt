package com.bottari.bottari.core.domain.usecase

import com.bottari.bottari.core.domain.repository.AppConfigRepository
import dev.zacsweers.metro.Inject

@Inject
class SavePermissionFlagUseCase(
    private val repository: AppConfigRepository,
) {
    suspend operator fun invoke(flag: Boolean): Result<Unit> = repository.savePermissionFlag(flag)
}
