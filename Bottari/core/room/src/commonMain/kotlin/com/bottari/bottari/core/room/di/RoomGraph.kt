package com.bottari.bottari.core.room.di

import com.bottari.bottari.core.room.InMemoryLocalDatabase
import com.bottari.bottari.core.room.LocalDatabase
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesTo
import dev.zacsweers.metro.Provides

@ContributesTo(AppScope::class)
interface RoomGraph {
    @Provides
    fun provideLocalDatabase(): LocalDatabase = InMemoryLocalDatabase()
}
