package com.snc.device.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.snc.device.presentation.DiscoverDevicesPageViewModel
import com.snc.device.presentation.components.CommonUi
import com.snc.device.scan_and_connect_device.generated.resources.Res
import com.snc.device.scan_and_connect_device.generated.resources.ic_bluetooth_vector
import com.snc.device.scan_and_connect_device.generated.resources.turn_on_bluetooth_ui_heading
import com.snc.device.scan_and_connect_device.generated.resources.turn_on_bluetooth_ui_message

@Composable
fun EnableBluetoothUi(
    discoverDevicesPageViewModel: DiscoverDevicesPageViewModel,
    modifier: Modifier = Modifier
) {
    CommonUi(
        heading = Res.string.turn_on_bluetooth_ui_heading,
        message = Res.string.turn_on_bluetooth_ui_message,
        uiIcon = Res.drawable.ic_bluetooth_vector,
        buttonLabel = Res.string.turn_on_bluetooth_ui_heading,
        modifier = modifier,
        onButtonClick = {

        }
    )
}