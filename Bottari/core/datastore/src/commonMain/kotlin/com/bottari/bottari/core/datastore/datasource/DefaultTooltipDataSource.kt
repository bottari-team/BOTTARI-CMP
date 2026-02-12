package com.bottari.bottari.core.datastore.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import com.bottari.bottari.core.datastore.datastore.BottariDataStoreFactory
import com.bottari.bottari.core.model.config.TooltipType
import dev.zacsweers.metro.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Inject
class DefaultTooltipDataSource(
    private val dataStoreFactory: BottariDataStoreFactory,
) : TooltipDataSource {
    private val dataStore: DataStore<Preferences> by lazy { dataStoreFactory.create(DATA_STORE_NAME) }

    override suspend fun isTooltipDismissed(tooltipType: TooltipType): Flow<Boolean> =
        dataStore.data.map { preferences ->
            val dismissedTooltips = preferences[KEY] ?: emptySet()
            dismissedTooltips.contains(tooltipType.name)
        }

    override suspend fun setTooltipDismissed(tooltipType: TooltipType) {
        dataStore.edit { preferences ->
            val dismissedTooltips = preferences[KEY] ?: emptySet()
            preferences[KEY] = dismissedTooltips + tooltipType.name
        }
    }

    companion object {
        private val KEY = stringSetPreferencesKey(name = "tooltip_dismissals")
        internal const val DATA_STORE_NAME = "tooltip_dismissals"
    }
}
