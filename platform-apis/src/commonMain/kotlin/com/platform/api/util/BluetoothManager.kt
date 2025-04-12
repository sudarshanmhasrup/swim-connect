package com.platform.api.util

import co.touchlab.kermit.Logger
import com.platform.api.model.SwimConnectDevice
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

object BluetoothManager {
    private val blueFalcon = BlueFalconManager.getBlueFalcon()

    val log = Logger.withTag("MyApp")

    fun startScanning(onDeviceFound: (SwimConnectDevice) -> Unit) {
        blueFalcon.scan()
        CoroutineScope(Dispatchers.IO).launch {
            log.i { "Started coroutine to collect peripherals" }
            blueFalcon.peripherals.collect { foundDevicesList ->
                log.i { "Collected a list of ${foundDevicesList.size} devices" }
                foundDevicesList.forEach { device ->
                    val name = device.name ?: "Unknown device"
                    val uuid = device.uuid
                    val device = SwimConnectDevice(name = name, address = uuid)
                    log.i { "Found device: $name ($uuid)" }
                    onDeviceFound(device)
                }
            }
        }
    }

}