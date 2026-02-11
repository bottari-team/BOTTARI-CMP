package com.bottari.bottari.core.network.util

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.installations.installations
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class FirebaseInstallationIdProvider {
    private val mutex = Mutex()
    private var cachedId: String? = null

    suspend fun getInstallationId(forceRefresh: Boolean = false): String {
        if (!forceRefresh) {
            cachedId?.let { return it }
        }

        return mutex.withLock {
            if (!forceRefresh) {
                cachedId?.let { return it }
            }

            val id = Firebase.installations.getId()
            cachedId = id
            id
        }
    }
}
