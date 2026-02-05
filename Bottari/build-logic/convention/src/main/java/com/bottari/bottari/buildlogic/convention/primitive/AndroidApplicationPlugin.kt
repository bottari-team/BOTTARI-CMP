package com.bottari.bottari.buildlogic.convention.primitive

import com.android.build.api.dsl.CommonExtension
import com.bottari.bottari.buildlogic.convention.extension.android
import com.bottari.bottari.buildlogic.convention.extension.androidApplication
import com.bottari.bottari.buildlogic.convention.extension.libs
import com.bottari.bottari.buildlogic.convention.extension.version
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project

@Suppress("unused")
class AndroidApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
            }
            androidApplication {
                android {
                    namespace?.let { this.namespace = it }
                    compileSdkVersion(libs.version("compileSdk").toInt())
                    defaultConfig {
                        applicationId = "com.bottari.bottari"
                        minSdk = libs.version("minSdk").toInt()
                        targetSdk = libs.version("targetSdk").toInt()
                        versionCode = libs.version("versionCode").toInt()
                        versionName = libs.version("versionName")
                    }

                    buildFeatures {
                        compose = true
                        buildConfig = true
                    }

                    composeOptions {
                        kotlinCompilerExtensionVersion = libs.version("compose")
                    }

                    packaging {
                        resources {
                            excludes +=
                                listOf(
                                    "/META-INF/{AL2.0,LGPL2.1}",
                                    "META-INF/INDEX.LIST",
                                )
                        }
                    }

                    buildTypes {
                        getByName("release") {
                            isMinifyEnabled = false
                        }
                    }

                    compileOptions {
                        sourceCompatibility = JavaVersion.VERSION_21
                        targetCompatibility = JavaVersion.VERSION_21
                    }
                }

                testOptions {
                    unitTests {
                        isIncludeAndroidResources = true
                    }
                }

                (this as CommonExtension<*, *, *, *, *, *>).lint {
                    val filename = displayName.replace(":", "_").replace("[\\s']".toRegex(), "")

                    xmlReport = true
                    xmlOutput =
                        rootProject.layout.buildDirectory.file("lint-reports/lint-results-$filename.xml")
                            .get().asFile

                    htmlReport = true
                    htmlOutput =
                        rootProject.layout.buildDirectory.file("lint-reports/lint-results-$filename.html")
                            .get().asFile

                    sarifReport = false
                }
            }
        }
    }
}



