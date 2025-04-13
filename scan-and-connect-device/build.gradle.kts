plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose.multiplatform)
}

group = libs.versions.scanAndConnectDevice.groupId.get()
version = libs.versions.scanAndConnectDevice.version.get()

kotlin {
    androidTarget()

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(libs.permissions.location)
            implementation(libs.permissions.bluetooth)
            implementation(libs.permissions.compose)
            implementation(libs.permissions)
            implementation(libs.lifecycle.viewmodel.compose)
            implementation(compose.material3)
            implementation(libs.navigation.compose)
            implementation(compose.components.resources)
            implementation(compose.foundation)
            implementation(compose.ui)
            implementation(project(":design-system"))
            implementation(project(":shared"))
            implementation(project(":platform-apis"))
        }
    }
}

android {
    namespace = libs.versions.scanAndConnectDevice.groupId.get()
    compileSdk = libs.versions.android.compileSdk.get().toInt()
}

// Custom build directory
layout.buildDirectory.set(file("$rootDir/.build/scanAndConnectDevice"))