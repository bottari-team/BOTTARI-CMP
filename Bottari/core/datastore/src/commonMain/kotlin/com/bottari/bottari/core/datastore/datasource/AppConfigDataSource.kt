package com.bottari.bottari.core.datastore.datasource

import kotlinx.coroutines.flow.Flow

interface AppConfigDataSource {
    suspend fun savePermissionFlag(flag: Boolean)

    fun getPermissionFlag(): Flow<Boolean>
}
