import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    id("bottari.convention.lint")
    id("bottari.convention.kmp")
    id("bottari.convention.kmp.compose")
    id("bottari.convention.kmp.ios")
    id("bottari.convention.android.application")
    id("bottari.convention.buildkonfig")
    alias(libs.plugins.gms)
}

android.namespace = "com.bottari.bottari"

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_21)
        }
    }

    targets.filterIsInstance<KotlinNativeTarget>().forEach { target ->
        target.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
            binaryOption("bundleId", "com.bottari.bottari")
            binaryOption("bundleVersion", libs.versions.versionName.toString())
            binaryOption("bundleShortVersionString", libs.versions.versionName.toString())
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.data)
            implementation(projects.core.domain)
            implementation(projects.core.common)

            implementation(libs.androidx.lifecycle.runtime.compose)
            implementation(libs.androidx.lifecycle.viewmodel.compose)
            implementation("dev.zacsweers.metro:metrox-viewmodel-compose:0.10.2")
        }

        androidMain.dependencies {
            implementation(libs.androidx.activity.compose)
            implementation("dev.zacsweers.metro:metrox-android:0.10.2")
        }
    }
}
