package com.platform.api.model

import dev.bluefalcon.BluetoothPeripheral

data class SwimConnectDevice(
    val name: String,
    val address: String,
    val peripheral: BluetoothPeripheral
)