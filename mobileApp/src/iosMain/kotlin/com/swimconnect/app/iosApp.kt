package com.swimconnect.app

import androidx.compose.ui.window.ComposeUIViewController
import com.platform.api.util.BlueFalconManager
import platform.UIKit.UIViewController

fun iosApp(): UIViewController {
    val uiViewController = ComposeUIViewController {
        ComposeApp()
    }

    // Initialize managers
    initializeManagers()

    return uiViewController
}

private fun initializeManagers() {
    BlueFalconManager.initialize()
}