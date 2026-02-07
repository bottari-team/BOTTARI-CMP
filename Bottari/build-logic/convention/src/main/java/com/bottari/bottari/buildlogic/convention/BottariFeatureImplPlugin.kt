package com.bottari.bottari.buildlogic.convention

import com.bottari.bottari.buildlogic.convention.extension.kotlin
import com.bottari.bottari.buildlogic.convention.extension.library
import com.bottari.bottari.buildlogic.convention.extension.libs
import com.bottari.bottari.buildlogic.convention.primitive.KmpAndroidPlugin
import com.bottari.bottari.buildlogic.convention.primitive.KmpComposePlugin
import com.bottari.bottari.buildlogic.convention.primitive.KmpIosPlugin
import com.bottari.bottari.buildlogic.convention.primitive.KmpPlugin
import com.bottari.bottari.buildlogic.convention.primitive.KotlinSerializationPlugin
import com.bottari.bottari.buildlogic.convention.primitive.LintPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply

@Suppress("unused")
class BottariFeatureImplPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply<LintPlugin>()
            apply<KmpPlugin>()
            apply<KmpAndroidPlugin>()
            apply<KmpIosPlugin>()
            apply<KmpComposePlugin>()
            apply<KotlinSerializationPlugin>()
            pluginManager.apply("dev.zacsweers.metro")

            kotlin {
                with(sourceSets) {
                    commonMain.dependencies {
                        implementation(project(":core:di"))
                        implementation(project(":core:ui"))
                        implementation(project(":core:designsystem"))
                        implementation(project(":core:model"))
                        implementation(project(":core:common"))
                        implementation(project(":core:domain"))

                        implementation(libs.library("navigation3"))
                        implementation(libs.library("kotlinx-serialization-json"))
                        implementation(libs.library("kotlinx-datetime"))

                        implementation(libs.library("androidx-lifecycle-runtime-compose"))
                        implementation(libs.library("androidx-lifecycle-viewmodel-compose"))
                    }
                }
            }
        }
    }
}
