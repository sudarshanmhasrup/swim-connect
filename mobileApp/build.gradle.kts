plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose.compiler)
}

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
}

android {
    namespace = libs.versions.android.applicationId.get()
    compileSdk = libs.versions.android.compileSdk.get().toInt()
}