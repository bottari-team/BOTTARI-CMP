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
                        implementation("org.jetbrains.compose.runtime:runtime:1.10.0")
                        implementation("org.jetbrains.compose.foundation:foundation:1.10.0")
                        implementation("org.jetbrains.compose.material3:material3:1.9.0")
                        implementation("org.jetbrains.compose.material:material-icons-extended:1.7.3")
                        implementation("org.jetbrains.compose.ui:ui:1.10.0")
                        implementation("org.jetbrains.compose.components:components-resources:1.10.0")
                        implementation("org.jetbrains.compose.ui:ui-tooling-preview:1.10.0")
                        implementation(libs.library("compose-material3"))
                    }
                    find { it.name == "androidMain" }?.apply {
                        dependencies {
                            implementation("org.jetbrains.compose.ui:ui-tooling-preview:1.10.0")
                        }
                    }
                }
            }
        }
    }
}
