package com.bottari.bottari.core.datastore.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.bottari.bottari.core.datastore.datastore.BottariDataStoreFactory
import dev.zacsweers.metro.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Inject
class DefaultAppConfigDataSource(
    private val dataStoreFactory: BottariDataStoreFactory,
) : AppConfigDataSource {
    private val dataStore: DataStore<Preferences> by lazy { dataStoreFactory.create(DATA_STORE_NAME) }

    override suspend fun savePermissionFlag(flag: Boolean) {
        dataStore.edit { preferences ->
            preferences[KEY] = flag
        }
    }

    override fun getPermissionFlag(): Flow<Boolean> =
        dataStore.data.map { preferences ->
            preferences[KEY] ?: false
        }

    companion object {
        private val KEY = booleanPreferencesKey(name = "KEY_PERMISSION_FLAG")
        internal const val DATA_STORE_NAME = "app_config"
    }
}
