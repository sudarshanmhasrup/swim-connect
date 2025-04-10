plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
}

group = libs.versions.platformApis.groupId.get()
version = libs.versions.platformApis.version.get()

kotlin {
    androidTarget()

    iosX64()
    iosArm64()
    iosSimulatorArm64()
}

android {
    namespace = libs.versions.platformApis.groupId.get()
    compileSdk = libs.versions.android.compileSdk.get().toInt()
}

// Custom build directory
layout.buildDirectory.set(file("$rootDir/.build/platformApis"))