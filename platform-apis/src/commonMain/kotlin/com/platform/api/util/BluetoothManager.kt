package com.platform.api.util

import com.platform.api.model.SwimConnectDevice
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

object BluetoothManager {
    private val blueFalcon = BlueFalconManager.getBlueFalcon()

    fun startScanning(
        onDiscoveryStarted: () -> Unit,
        onDeviceFound: (SwimConnectDevice) -> Unit,
        onBluetoothDisabledError: () -> Unit,
        onLocationDisabledError: () -> Unit,
    ) {
        // Check if bluetooth is enabled
        blueFalcon.scan()

        // Start discovery
        CoroutineScope(Dispatchers.IO).launch {
            blueFalcon.peripherals
                .onStart {
                    onDiscoveryStarted()
                }
                .collect { foundDevicesList ->
                    foundDevicesList.forEach { device ->
                        val name = device.name ?: "Unknown device"
                        val uuid = device.uuid
                        val device = SwimConnectDevice(
                            name = name,
                            address = uuid,
                            peripheral = device
                        )
                        onDeviceFound(device)
                    }
                }

        }
    }

    fun stopScanning() {
        blueFalcon.stopScanning()
    }
    
    fun connectDevice(device: SwimConnectDevice) {
        blueFalcon.connect(device.peripheral)
    }
}