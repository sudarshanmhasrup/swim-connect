plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.compiler)
}

group = libs.versions.shared.groupId.get()
version = libs.versions.shared.version.get()

kotlin {
    androidTarget()

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(libs.lifecycle.viewmodel.compose)
            implementation(compose.material3)
            implementation(compose.components.resources)
            implementation(compose.foundation)
            implementation(compose.ui)
            implementation(project(":designSystem"))
        }
    }
}

android {
    namespace = libs.versions.shared.groupId.get()
    compileSdk = libs.versions.android.compileSdk.get().toInt()
}

// Customize build directory
layout.buildDirectory.set(file("$rootDir/.build/shared"))