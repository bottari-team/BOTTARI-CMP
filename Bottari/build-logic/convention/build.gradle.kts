plugins {
    `kotlin-dsl`
}

group = "com.bottari.bottari.buildlogic.convention"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21
    }
}

dependencies {
    compileOnly(libs.bundles.plugins)
    compileOnly(libs.buildkonfig.compiler)
    implementation(libs.buildkonfigGradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "bottari.convention.android.application"
            implementationClass =
                "com.bottari.bottari.buildlogic.convention.primitive.AndroidApplicationPlugin"
        }

        register("kmp") {
            id = "bottari.convention.kmp"
            implementationClass = "com.bottari.bottari.buildlogic.convention.primitive.KmpPlugin"
        }

        register("kmpAndroid") {
            id = "bottari.convention.kmp.android"
            implementationClass =
                "com.bottari.bottari.buildlogic.convention.primitive.KmpAndroidPlugin"
        }

        register("kmpCompose") {
            id = "bottari.convention.kmp.compose"
            implementationClass =
                "com.bottari.bottari.buildlogic.convention.primitive.KmpComposePlugin"
        }

        register("kmpIos") {
            id = "bottari.convention.kmp.ios"
            implementationClass = "com.bottari.bottari.buildlogic.convention.primitive.KmpIosPlugin"
        }

        register("lint") {
            id = "bottari.convention.lint"
            implementationClass = "com.bottari.bottari.buildlogic.convention.primitive.LintPlugin"
        }

        register("spotless") {
            id = "bottari.convention.spotless"
            implementationClass =
                "com.bottari.bottari.buildlogic.convention.primitive.SpotlessPlugin"
        }

        register("detekt") {
            id = "bottari.convention.detekt"
            implementationClass = "com.bottari.bottari.buildlogic.convention.primitive.DetektPlugin"
        }

        register("buildkonfig") {
            id = "bottari.convention.buildkonfig"
            implementationClass =
                "com.bottari.bottari.buildlogic.convention.primitive.BuildKonfigPlugin"
        }

        register("featureApi") {
            id = "bottari.convention.feature.api"
            implementationClass =
                "com.bottari.bottari.buildlogic.convention.BottariFeatureApiPlugin"
        }

        register("featureImpl") {
            id = "bottari.convention.feature.impl"
            implementationClass =
                "com.bottari.bottari.buildlogic.convention.BottariFeatureImplPlugin"
        }
    }
}
