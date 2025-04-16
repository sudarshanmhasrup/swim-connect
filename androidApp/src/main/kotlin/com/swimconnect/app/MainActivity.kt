package com.swimconnect.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.platform.api.util.BlueFalconManager
import com.platform.api.util.DataStoreManager
import com.snc.device.util.DiscoverDevicesPageLifecycleManager

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()

        // Initialize managers
        initializeManagers()

        setContent {
            AndroidApp()
        }
    }

    override fun onResume() {
        super.onResume()
        DiscoverDevicesPageLifecycleManager.onResume()
    }

    private fun initializeManagers() {
        DataStoreManager.initialize(context = this)
        BlueFalconManager.initialize(applicationContext = this.application)
    }
}