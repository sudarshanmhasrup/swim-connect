package com.snc.device.data

import com.snc.device.model.RuntimePermission
import com.snc.device.scan_and_connect_device.generated.resources.Res
import com.snc.device.scan_and_connect_device.generated.resources.ic_location
import com.snc.device.scan_and_connect_device.generated.resources.ic_nearby_devices
import com.snc.device.scan_and_connect_device.generated.resources.location_permission_description
import com.snc.device.scan_and_connect_device.generated.resources.location_permission_label
import com.snc.device.scan_and_connect_device.generated.resources.nearby_devices_permission_description
import com.snc.device.scan_and_connect_device.generated.resources.nearby_devices_permission_label
import dev.icerock.moko.permissions.Permission
import dev.icerock.moko.permissions.bluetooth.BLUETOOTH_CONNECT
import dev.icerock.moko.permissions.bluetooth.BLUETOOTH_SCAN
import dev.icerock.moko.permissions.location.LOCATION

val androidPermissionsList = listOf(
    RuntimePermission(
        image = Res.drawable.ic_nearby_devices,
        label = Res.string.nearby_devices_permission_label,
        description = Res.string.nearby_devices_permission_description,
        isPermissionGranted = false,
        permission = Permission.BLUETOOTH_SCAN,
        showOnUi = true
    ),
    RuntimePermission(
        image = Res.drawable.ic_location,
        label = Res.string.location_permission_label,
        description = Res.string.location_permission_description,
        isPermissionGranted = false,
        permission = Permission.LOCATION,
        showOnUi = true
    ),
    RuntimePermission(
        isPermissionGranted = false,
        permission = Permission.BLUETOOTH_CONNECT,
        showOnUi = false
    )
)