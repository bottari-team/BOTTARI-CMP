plugins {
    id("bottari.convention.lint")
    id("bottari.convention.kmp")
    id("bottari.convention.kmp.android")
    id("bottari.convention.kmp.ios")
}

android.namespace = "com.bottari.bottari.core.navigation"

kotlin {
    sourceSets {
        androidMain.dependencies {}
    }
}
