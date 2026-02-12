package com.bottari.bottari.core.network.di

import com.bottari.bottari.core.network.BuildKonfig
import com.bottari.bottari.core.network.client.createHttpClient
import com.bottari.bottari.core.network.service.MemberService
import com.bottari.bottari.core.network.service.createMemberService
import com.bottari.bottari.core.network.util.FirebaseInstallationIdProvider
import de.jensklingenberg.ktorfit.Ktorfit
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.Provides
import dev.zacsweers.metro.SingleIn
import io.ktor.client.HttpClient

interface NetworkGraph {
    @Provides
    @SingleIn(AppScope::class)
    fun provideHttpClient(
        fidProvider: FirebaseInstallationIdProvider,
    ): HttpClient = createHttpClient(fidProvider)

    @Provides
    @SingleIn(AppScope::class)
    fun provideKtrofit(
        client: HttpClient,
    ): Ktorfit =
        Ktorfit
            .Builder()
            .httpClient(client)
            .baseUrl(BuildKonfig.BASE_URL)
            .build()

    @Provides
    @SingleIn(AppScope::class)
    fun provideFidProvider(): FirebaseInstallationIdProvider = FirebaseInstallationIdProvider()

    @Provides
    @SingleIn(AppScope::class)
    fun provideMemberService(ktorfit: Ktorfit): MemberService = ktorfit.createMemberService()
}
