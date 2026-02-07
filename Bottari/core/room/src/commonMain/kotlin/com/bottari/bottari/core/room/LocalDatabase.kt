package com.bottari.bottari.core.room

interface LocalDatabase {
    fun upsert(table: String, value: String)

    fun query(table: String): List<String>
}

internal class InMemoryLocalDatabase : LocalDatabase {
    private val tables = mutableMapOf<String, MutableList<String>>()

    override fun upsert(table: String, value: String) {
        val entries = tables.getOrPut(table) { mutableListOf() }
        entries += value
    }

    override fun query(table: String): List<String> = tables[table].orEmpty()
}
