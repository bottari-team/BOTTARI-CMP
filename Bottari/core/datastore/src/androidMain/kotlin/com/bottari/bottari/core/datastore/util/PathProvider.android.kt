package com.bottari.bottari.core.datastore.util

import android.content.Context
import dev.zacsweers.metro.Inject

@Inject
actual class PathProvider(
    private val context: Context,
) {
    actual fun getPath(fileName: String): String {
        return context.filesDir.resolve(fileName).absolutePath
    }
}
