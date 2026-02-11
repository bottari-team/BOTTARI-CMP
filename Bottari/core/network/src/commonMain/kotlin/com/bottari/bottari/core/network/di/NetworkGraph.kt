package com.bottari.bottari.core.network.di

import com.bottari.bottari.core.di.DataScope
import com.bottari.bottari.core.network.BuildKonfig
import com.bottari.bottari.core.network.util.FirebaseInstallationIdProvider
import com.bottari.bottari.core.network.client.createHttpClient
import com.bottari.bottari.core.network.service.MemberService
import de.jensklingenberg.ktorfit.Ktorfit
import dev.zacsweers.metro.ContributesTo
import dev.zacsweers.metro.Provides
import io.ktor.client.HttpClient

@ContributesTo(DataScope::class)
interface NetworkGraph {
    @Provides
    fun provideHttpClient(
        fidProvider: FirebaseInstallationIdProvider,
    ): HttpClient = createHttpClient(fidProvider)

    @Provides
    fun provideKtrofit(
        client: HttpClient,
    ): Ktorfit =
        Ktorfit
            .Builder()
            .httpClient(client)
            .baseUrl(BuildKonfig.BASE_URL)
            .build()

    @Provides
    fun provideMemberService(ktorfit: Ktorfit): MemberService = ktorfit.create<MemberService>()

    @Provides
    fun provideFidProvider(): FirebaseInstallationIdProvider = FirebaseInstallationIdProvider()
}
