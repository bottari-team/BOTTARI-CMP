plugins {
    id("bottari.convention.lint")
    id("bottari.convention.kmp")
    id("bottari.convention.kmp.android")
    id("bottari.convention.kmp.ios")
    id("dev.zacsweers.metro")
}

android.namespace = "com.bottari.bottari.core.datastore"

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.di)
            implementation(projects.core.model)

            implementation(libs.androidx.datastore)
            api(libs.androidx.datastore.preferences)
        }
    }
}
