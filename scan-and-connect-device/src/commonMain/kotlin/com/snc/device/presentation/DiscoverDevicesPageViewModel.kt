package com.snc.device.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.platform.api.TargetPlatform
import com.platform.api.getPlatform
import com.platform.api.model.SwimConnectDevice
import com.platform.api.util.BluetoothManager
import com.snc.device.domain.RuntimePermissionsUseCase
import com.snc.device.model.RuntimePermission
import dev.icerock.moko.permissions.DeniedAlwaysException
import dev.icerock.moko.permissions.DeniedException
import dev.icerock.moko.permissions.PermissionState
import dev.icerock.moko.permissions.PermissionsController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DiscoverDevicesPageViewModel(
    private val permissionController: PermissionsController
) : ViewModel() {
    private val _discoverDevicesPageUiState = MutableStateFlow(DiscoverDevicesPageUiState())
    val discoverDevicesPageUiState = _discoverDevicesPageUiState.asStateFlow()

    private val currentPlatform = getPlatform()

    init {
        if (currentPlatform == TargetPlatform.IOS) {
            _discoverDevicesPageUiState.value = _discoverDevicesPageUiState.value.copy(
                allPermissionsAreGranted = true,
                isLocationEnabled = true
            )
        } else {
            _discoverDevicesPageUiState.value = _discoverDevicesPageUiState.value.copy(
                runtimePermissionsList = RuntimePermissionsUseCase.getRuntimePermissionsList()
            )
            checkIfAllPermissionsAreGranted()
        }
    }

    fun checkIfAllPermissionsAreGranted() {
        viewModelScope.launch {
            updateRuntimePermissionListInternal()
            val allGranted = areAllPermissionsGranted()
            val mainGranted = areMainPermissionsGranted()

            _discoverDevicesPageUiState.value = _discoverDevicesPageUiState.value.copy(
                allPermissionsAreGranted = allGranted,
                mainPermissionAreGranted = mainGranted
            )
        }
    }

    private suspend fun updateRuntimePermissionListInternal() {
        _discoverDevicesPageUiState.value = _discoverDevicesPageUiState.value.copy(
            runtimePermissionsList = discoverDevicesPageUiState.value.runtimePermissionsList.map {
                it.copy(
                    isPermissionGranted = permissionController.isPermissionGranted(it.permission)
                )
            }
        )
    }

    private suspend fun areAllPermissionsGranted(): Boolean {
        return _discoverDevicesPageUiState.value.runtimePermissionsList.all {
            permissionController.getPermissionState(it.permission) !in listOf(
                PermissionState.Denied,
                PermissionState.DeniedAlways,
                PermissionState.NotGranted,
                PermissionState.NotDetermined
            )
        }
    }

    private suspend fun areMainPermissionsGranted(): Boolean {
        return _discoverDevicesPageUiState.value.runtimePermissionsList
            .filter { it.showOnUi }
            .all { permissionController.isPermissionGranted(it.permission) }
    }

    fun requestRuntimePermission(runtimePermission: RuntimePermission) {
        viewModelScope.launch {
            try {
                permissionController.providePermission(permission = runtimePermission.permission)
            } catch (_: DeniedAlwaysException) {
                permissionController.openAppSettings()
            } catch (_: DeniedException) {

            }
            checkIfAllPermissionsAreGranted()
        }
    }

    fun requestBackgroundPermissions() {
        viewModelScope.launch {
            _discoverDevicesPageUiState.value.runtimePermissionsList.forEach {
                if (!it.showOnUi) {
                    try {
                        permissionController.providePermission(permission = it.permission)
                    } catch (_: DeniedException) {
                        TODO()
                    } catch (_: DeniedAlwaysException) {
                        TODO()
                    }
                }
            }
            checkIfAllPermissionsAreGranted()
        }
    }

    fun updateScannedDevicesList(device: SwimConnectDevice) {
        val deviceList = _discoverDevicesPageUiState.value.deviceList
            .toMutableList()
        deviceList.add(device)
        _discoverDevicesPageUiState.value = _discoverDevicesPageUiState.value
            .copy(deviceList = deviceList)
    }

    fun startScanning() {
        BluetoothManager.startScanning(
            onDiscoveryStarted = {
                _discoverDevicesPageUiState.value =
                    _discoverDevicesPageUiState.value.copy(isScanning = true)
            },
            onDeviceFound = {
                val currentDevice = it
                if (currentDevice !in _discoverDevicesPageUiState.value.deviceList) {
                    updateScannedDevicesList(device = it)
                }
            },
            onBluetoothDisabledError = {},
            onLocationDisabledError = {}
        )

        viewModelScope.launch(Dispatchers.IO) {
            delay(30_000)
            stopScanning()
        }
    }

    fun startRescanning() {
        _discoverDevicesPageUiState.value =
            _discoverDevicesPageUiState.value.copy(deviceList = emptyList())
        startScanning()
    }

    fun stopScanning() {
        BluetoothManager.stopScanning()
        _discoverDevicesPageUiState.value =
            _discoverDevicesPageUiState.value.copy(isScanning = false)
    }

    fun connect(device: SwimConnectDevice) {
        BluetoothManager.connectDevice(device = device)
    }
}