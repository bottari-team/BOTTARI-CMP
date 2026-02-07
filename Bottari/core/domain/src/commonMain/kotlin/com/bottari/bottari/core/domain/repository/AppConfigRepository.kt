package com.bottari.bottari.core.domain.repository

import kotlinx.coroutines.flow.Flow

interface AppConfigRepository {
    suspend fun savePermissionFlag(flag: Boolean): Result<Unit>

    fun getPermissionFlag(): Flow<Boolean>
}
