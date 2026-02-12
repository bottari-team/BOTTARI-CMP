package com.bottari.bottari.core.datastore.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import com.bottari.bottari.core.datastore.util.PathProvider
import dev.zacsweers.metro.Inject
import okio.Path.Companion.toPath

@Inject
class BottariDataStoreFactory(
    private val pathProvider: PathProvider,
) {
    fun create(fileName: String): DataStore<Preferences> =
        PreferenceDataStoreFactory.createWithPath(
            produceFile = { pathProvider.getPath(fileName + EXTENSION).toPath() },
        )

    companion object {
        private const val EXTENSION: String = ".preferences_pb"
    }
}
