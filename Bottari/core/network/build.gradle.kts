import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.BOOLEAN
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING

plugins {
    id("bottari.convention.lint")
    id("bottari.convention.kmp")
    id("bottari.convention.kmp.android")
    id("bottari.convention.kmp.ios")
    id("bottari.convention.buildkonfig")
    id("dev.zacsweers.metro")
}

android.namespace = "com.bottari.bottari.core.network"

buildkonfig {
    packageName = "com.bottari.bottari.core.network"

    defaultConfigs {
        buildConfigField(
            STRING,
            "BASE_URL",
            "${gradleLocalProperties(rootDir, providers).getProperty("DEBUG_BASE_URL")}",
        )
        buildConfigField(BOOLEAN, "IS_DEBUG", "true")
    }

    defaultConfigs("release") {
        buildConfigField(
            STRING,
            "BASE_URL",
            "${gradleLocalProperties(rootDir, providers).getProperty("RELEASE_BASE_URL")}",
        )
        buildConfigField(BOOLEAN, "IS_DEBUG", "false")
    }
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.di)

            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.client.logging)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.ktorfit)

            implementation(libs.firebase.installations)
        }

        androidMain.dependencies {
            implementation(libs.ktor.client.okhttp)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
    }
}
