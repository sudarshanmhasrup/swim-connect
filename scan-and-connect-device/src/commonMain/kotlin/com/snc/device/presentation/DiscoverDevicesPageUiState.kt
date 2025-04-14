package com.snc.device.presentation

import com.platform.api.model.SwimConnectDevice
import com.snc.device.model.RuntimePermission

data class DiscoverDevicesPageUiState(
    val isScanning: Boolean = false,
    val deviceList: List<SwimConnectDevice> = emptyList(),
    val allPermissionsAreGranted: Boolean = false,
    val mainPermissionAreGranted: Boolean = false,
    val runtimePermissionsList: List<RuntimePermission> = emptyList(),
    val isBluetoothEnabled: Boolean = false,
    val isLocationEnabled: Boolean = false
)
