plugins {
    id("bottari.convention.lint")
    id("bottari.convention.kmp")
    id("bottari.convention.kmp.android")
    id("bottari.convention.kmp.ios")
    id("dev.zacsweers.metro")
}

android.namespace = "com.bottari.bottari.core.domain"

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.model)

            implementation(libs.kotlinx.coroutines.core)
        }
    }
}
