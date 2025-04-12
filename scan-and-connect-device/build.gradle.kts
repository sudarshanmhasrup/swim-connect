plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose.multiplatform)
}

group = libs.versions.childDetails.groupId.get()
version = libs.versions.childDetails.version.get()

kotlin {
    androidTarget()

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(libs.lifecycle.viewmodel.compose)
            implementation(libs.navigation.compose)
            implementation(compose.components.resources)
            implementation(compose.foundation)
            implementation(compose.ui)
            implementation(project(":design-system"))
            implementation(project(":platform-apis"))
            implementation(project(":shared"))
        }
    }
}

android {
    namespace = libs.versions.childDetails.groupId.get()
    compileSdk = libs.versions.android.compileSdk.get().toInt()
}

// Custom build directory
layout.buildDirectory.set(file("$rootDir/.build/scanAndConnectDevice"))