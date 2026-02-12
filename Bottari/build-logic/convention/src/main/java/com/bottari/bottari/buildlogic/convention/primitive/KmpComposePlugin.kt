package com.bottari.bottari.buildlogic.convention.primitive

import com.bottari.bottari.buildlogic.convention.extension.android
import com.bottari.bottari.buildlogic.convention.extension.kotlin
import com.bottari.bottari.buildlogic.convention.extension.library
import com.bottari.bottari.buildlogic.convention.extension.libs
import org.gradle.api.Plugin
import org.gradle.api.Project

@Suppress("unused")
class KmpComposePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.compose")
                apply("org.jetbrains.kotlin.plugin.compose")
            }
            if (plugins.hasPlugin("com.android.library")) {
                android {
                    buildFeatures.compose = true
                }
            }
            kotlin {
                with(sourceSets) {
                    commonMain.dependencies {
                        implementation(libs.library("compose-runtime"))
                        implementation(libs.library("compose-foundation"))
                        implementation(libs.library("compose-ui"))
                        implementation(libs.library("compose-ui-tooling-preview"))
                        implementation(libs.library("compose-components-resources"))
                        implementation(libs.library("compose-material3"))
                        implementation("org.jetbrains.compose.material:material-icons-extended:1.7.3")
                    }
                    find { it.name == "androidMain" }?.apply {
                        dependencies {
                            implementation(libs.library("compose-ui-tooling"))
                        }
                    }
                }
            }
        }
    }
}
