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
    id("com.google.devtools.ksp")
    id("de.jensklingenberg.ktorfit")
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

ktorfit {
    compilerPluginVersion.set("2.3.3")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.model)

            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.client.logging)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.ktorfit)

            implementation(libs.firebase.installations)
            implementation(libs.firebase.messaging)
        }

        androidMain.dependencies {
            implementation(libs.ktor.client.okhttp)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
    }
}

dependencies {
    kspCommonMainMetadata(libs.ktorfit.ksp)
}
