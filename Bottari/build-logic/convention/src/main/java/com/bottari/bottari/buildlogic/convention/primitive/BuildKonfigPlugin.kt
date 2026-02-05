package com.bottari.bottari.buildlogic.convention.primitive

import com.bottari.bottari.buildlogic.convention.extension.libs
import com.bottari.bottari.buildlogic.convention.extension.version
import com.codingfeline.buildkonfig.compiler.FieldSpec
import com.codingfeline.buildkonfig.gradle.BuildKonfigExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

@Suppress("unused")
class BuildKonfigPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.codingfeline.buildkonfig")

            extensions.configure<BuildKonfigExtension> {
                packageName = "com.bottari.bottari"

                defaultConfigs {
                    buildConfigField(
                        FieldSpec.Type.STRING,
                        "versionName",
                        libs.version("versionName"),
                    )
                }
            }
        }
    }
}
