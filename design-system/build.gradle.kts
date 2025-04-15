plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.compiler)
}

group = libs.versions.designSystem.groupId.get()
version = libs.versions.designSystem.version.get()

kotlin {
    androidTarget()

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain {
            dependencies {
                implementation(compose.ui)
            }
        }
    }
}

android {
    namespace = libs.versions.designSystem.groupId.get()
    compileSdk = libs.versions.android.compileSdk.get().toInt()
}

// Customize build directory
layout.buildDirectory.set(file("$rootDir/.build/designSystem"))