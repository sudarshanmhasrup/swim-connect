package com.snc.device.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.snc.device.presentation.DiscoverDevicesPageViewModel
import com.snc.device.presentation.components.CommonUi
import com.snc.device.scan_and_connect_device.generated.resources.Res
import com.snc.device.scan_and_connect_device.generated.resources.ic_location
import com.snc.device.scan_and_connect_device.generated.resources.turn_on_location_ui_heading
import com.snc.device.scan_and_connect_device.generated.resources.turn_on_location_ui_message

@Composable
fun EnableLocationUi(
    discoverDevicesPageViewModel: DiscoverDevicesPageViewModel,
    modifier: Modifier = Modifier
) {
    CommonUi(
        heading = Res.string.turn_on_location_ui_heading,
        message = Res.string.turn_on_location_ui_message,
        uiIcon = Res.drawable.ic_location,
        buttonLabel = Res.string.turn_on_location_ui_heading,
        modifier = modifier,
        onButtonClick = {

        }
    )
}