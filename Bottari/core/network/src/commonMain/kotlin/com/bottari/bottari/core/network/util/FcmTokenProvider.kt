package com.bottari.bottari.core.network.util

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.messaging.messaging
import dev.zacsweers.metro.Inject
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

@Inject
class FcmTokenProvider {
    private val mutex = Mutex()
    private var cachedToken: String? = null

    suspend fun getToken(forceRefresh: Boolean = false): String {
        if (!forceRefresh) {
            cachedToken?.let { return it }
        }

        return mutex.withLock {
            if (!forceRefresh) {
                cachedToken?.let { return it }
            }

            val token = Firebase.messaging.getToken()
            cachedToken = token
            token
        }
    }
}
