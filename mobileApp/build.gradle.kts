plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose.multiplatform)
}

group = libs.versions.mobileApp.groupId.get()
version = libs.versions.mobileApp.version.get()

kotlin {
    androidTarget()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "SwimConnect"
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.lifecycle.viewmodel.compose)
            implementation(libs.navigation.compose)
            implementation(compose.components.resources)
            implementation(compose.foundation)
            implementation(compose.ui)
            implementation(project(":designSystem"))
            implementation(project(":shared"))
        }
    }
}

android {
    namespace = "com.swimconnect.xr"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
}

// Custom build directory
layout.buildDirectory.set(file("$rootDir/.build/mobileApp"))