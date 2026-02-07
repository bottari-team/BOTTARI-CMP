package com.bottari.bottari.core.datastore.util

expect class PathProvider {
    fun getPath(fileName: String): String
}
