plugins {
    id("bottari.convention.lint")
    id("bottari.convention.kmp")
    id("bottari.convention.kmp.android")
    id("bottari.convention.kmp.ios")
    id("dev.zacsweers.metro")
}

android.namespace = "com.bottari.bottari.core.data"

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.di)
            implementation(projects.core.domain)
            implementation(projects.core.model)

            api(projects.core.datastore)
            api(projects.core.network)

            implementation(libs.ktor.client.core)
            implementation(libs.kotlinx.coroutines.core)
        }
    }
}
