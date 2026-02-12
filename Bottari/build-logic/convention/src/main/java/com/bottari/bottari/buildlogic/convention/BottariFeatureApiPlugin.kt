package com.bottari.bottari.buildlogic.convention

import com.bottari.bottari.buildlogic.convention.extension.kotlin
import com.bottari.bottari.buildlogic.convention.extension.library
import com.bottari.bottari.buildlogic.convention.extension.libs
import com.bottari.bottari.buildlogic.convention.primitive.KmpAndroidPlugin
import com.bottari.bottari.buildlogic.convention.primitive.KmpIosPlugin
import com.bottari.bottari.buildlogic.convention.primitive.KotlinSerializationPlugin
import com.bottari.bottari.buildlogic.convention.primitive.LintPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply

@Suppress("unused")
class BottariFeatureApiPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply<LintPlugin>()
            apply<KmpAndroidPlugin>()
            apply<KmpIosPlugin>()
            apply<KotlinSerializationPlugin>()

            kotlin {
                with(sourceSets) {
                    commonMain.dependencies {
                        implementation(libs.library("navigation3"))
                        implementation(libs.library("kotlinx-serialization-json"))
                    }
                }
            }
        }
    }
}
