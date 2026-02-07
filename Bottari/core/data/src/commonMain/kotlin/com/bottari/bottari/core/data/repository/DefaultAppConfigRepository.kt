package com.bottari.bottari.core.data.repository

import com.bottari.bottari.core.datastore.datasource.AppConfigDataSource
import com.bottari.bottari.core.di.DataScope
import com.bottari.bottari.core.domain.repository.AppConfigRepository
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.SingleIn
import kotlinx.coroutines.flow.Flow

@Inject
@SingleIn(DataScope::class)
class DefaultAppConfigRepository(
    private val dataSource: AppConfigDataSource,
) : AppConfigRepository {
    override suspend fun savePermissionFlag(flag: Boolean): Result<Unit> =
        runCatching { dataSource.savePermissionFlag(flag) }

    override fun getPermissionFlag(): Flow<Boolean> = dataSource.getPermissionFlag()
}
