plugins {
    id("bottari.convention.lint")
    id("bottari.convention.kmp")
    id("bottari.convention.kmp.android")
    id("bottari.convention.kmp.ios")
    id("bottari.convention.kmp.compose")
}

android.namespace = "com.bottari.bottari.core.ui"

compose.resources {
    publicResClass = true
}
