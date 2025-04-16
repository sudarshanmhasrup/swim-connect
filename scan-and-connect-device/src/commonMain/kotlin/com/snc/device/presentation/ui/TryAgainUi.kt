package com.snc.device.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.snc.device.presentation.DiscoverDevicesPageViewModel
import com.snc.device.presentation.components.CommonUi
import com.snc.device.scan_and_connect_device.generated.resources.Res
import com.snc.device.scan_and_connect_device.generated.resources.ic_bluetooth_vector
import com.snc.device.scan_and_connect_device.generated.resources.try_again_button_label
import com.snc.device.scan_and_connect_device.generated.resources.try_again_ui_heading
import com.snc.device.scan_and_connect_device.generated.resources.try_again_ui_message

@Composable
fun TryAgainUi(
    discoverDevicesPageViewModel: DiscoverDevicesPageViewModel,
    modifier: Modifier = Modifier
) {
    CommonUi(
        heading = Res.string.try_again_ui_heading,
        message = Res.string.try_again_ui_message,
        uiIcon = Res.drawable.ic_bluetooth_vector,
        buttonLabel = Res.string.try_again_button_label,
        modifier = modifier,
        onButtonClick = {
            discoverDevicesPageViewModel.startRescanning()
        }
    )
}