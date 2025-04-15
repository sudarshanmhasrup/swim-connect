package com.snc.device.util

import com.snc.device.presentation.DiscoverDevicesPageViewModel

object DiscoverDevicesPageLifecycleManager {
    private var discoverDevicesPageViewModel: DiscoverDevicesPageViewModel? = null

    fun initialize(discoverDevicesPageViewModel: DiscoverDevicesPageViewModel) {
        this.discoverDevicesPageViewModel = discoverDevicesPageViewModel
    }

    fun onResume() {
        discoverDevicesPageViewModel?.checkIfAllPermissionsAreGranted()
    }
}