package com.bottari.bottari.buildlogic.convention.primitive

import com.diffplug.gradle.spotless.SpotlessExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

@Suppress("unused")
class SpotlessPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.diffplug.spotless")
            }
            extensions.configure<SpotlessExtension> {
                kotlin {
                    target("**/*.kt")
                    targetExclude("**/build/**/*.kt")
                    ktlint()
                    trimTrailingWhitespace()
                    endWithNewline()
                }
                format("kts") {
                    target("**/*.kts")
                    targetExclude("**/build/**/*.kts")
                    trimTrailingWhitespace()
                    endWithNewline()
                }
                format("misc") {
                    target("**/*.gradle", "**/*.md", "**/.gitignore")
                    trimTrailingWhitespace()
                    endWithNewline()
                }
            }
        }
    }
}
