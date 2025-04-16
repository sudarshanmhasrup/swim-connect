package com.snc.device.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorProducer
import androidx.compose.ui.unit.dp
import com.compose.shared.extentions.discoverDevicesPageHeadingAndMessageModifier
import com.compose.shared.extentions.foundDeviceListAdapterBoxModifier
import com.compose.shared.extentions.foundDeviceListAdapterModifier
import com.compose.shared.extentions.scanningStatusAndScanButtonModifier
import com.compose.shared.presentation.components.HeadingAndMessage
import com.design.system.api.ComposeAppTheme
import com.snc.device.presentation.DiscoverDevicesPageViewModel
import com.snc.device.presentation.components.FoundDeviceListAdapter
import com.snc.device.scan_and_connect_device.generated.resources.Res
import com.snc.device.scan_and_connect_device.generated.resources.discover_devices_page_message_done_scanning
import com.snc.device.scan_and_connect_device.generated.resources.discover_devices_page_message_scanning
import com.snc.device.scan_and_connect_device.generated.resources.discover_devices_page_title
import com.snc.device.scan_and_connect_device.generated.resources.done_scanning_label
import com.snc.device.scan_and_connect_device.generated.resources.rescan_button_label
import com.snc.device.scan_and_connect_device.generated.resources.scanning_devices_label
import org.jetbrains.compose.resources.stringResource

@Composable
fun DiscoverDevicesUi(
    discoverDevicesPageViewModel: DiscoverDevicesPageViewModel,
    modifier: Modifier = Modifier
) {
    val discoverDevicesPageUiState =
        discoverDevicesPageViewModel.discoverDevicesPageUiState.collectAsState()

    val message = if (discoverDevicesPageUiState.value.isScanning) {
        Res.string.discover_devices_page_message_scanning
    } else {
        Res.string.discover_devices_page_message_done_scanning
    }

    discoverDevicesPageViewModel.requestBackgroundPermissions()

    LazyColumn(verticalArrangement = Arrangement.spacedBy(0.dp), modifier = modifier) {
        item {
            HeadingAndMessage(
                heading = Res.string.discover_devices_page_title,
                message = message,
                modifier = Modifier.discoverDevicesPageHeadingAndMessageModifier()
            )
        }
        item {
            ScanningStatusAndScanButton(
                discoverDevicesPageViewModel = discoverDevicesPageViewModel,
                isScanning = discoverDevicesPageUiState.value.isScanning,
                modifier = Modifier.scanningStatusAndScanButtonModifier()
            )
        }
        itemsIndexed(discoverDevicesPageUiState.value.deviceList) { index, device ->
            val backgroundColor = ComposeAppTheme.colors.secondaryColor
            Box(modifier = Modifier.foundDeviceListAdapterBoxModifier()) {
                FoundDeviceListAdapter(
                    discoverDevicesPageViewModel = discoverDevicesPageViewModel,
                    device = device,
                    modifier = Modifier.foundDeviceListAdapterModifier(backgroundColor = backgroundColor)
                )
            }
            if (index != discoverDevicesPageUiState.value.deviceList.lastIndex) {
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@Composable
private fun ScanningStatusAndScanButton(
    discoverDevicesPageViewModel: DiscoverDevicesPageViewModel,
    isScanning: Boolean,
    modifier: Modifier = Modifier
) {
    val primaryFontColor = ComposeAppTheme.colors.primaryFontColor
    val scanningStatus = if (isScanning) {
        Res.string.scanning_devices_label
    } else {
        Res.string.done_scanning_label
    }
    val buttonColors = ButtonDefaults.buttonColors(
        containerColor = Color.Transparent,
        contentColor = ComposeAppTheme.colors.primaryColor,
        disabledContainerColor = Color.Transparent,
        disabledContentColor = ComposeAppTheme.colors.primaryDisabledColor
    )

    val discoverDevicesPageUiState = discoverDevicesPageViewModel.discoverDevicesPageUiState.collectAsState()

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            BasicText(
                text = stringResource(resource = scanningStatus),
                maxLines = 1,
                style = ComposeAppTheme.typography.bodyLabelLarge,
                color = ColorProducer { primaryFontColor }
            )
            Spacer(modifier = Modifier.width(8.dp))
            if (isScanning) {
                CircularProgressIndicator(
                    trackColor = ComposeAppTheme.colors.primaryColor,
                    color = ComposeAppTheme.colors.secondaryColor,
                    strokeWidth = 2.dp,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
        Button(
            colors = buttonColors,
            contentPadding = PaddingValues(0.dp),
            enabled = !isScanning,
            onClick = {
                discoverDevicesPageViewModel.checkIfAllPermissionsAreGranted()

                if (discoverDevicesPageUiState.value.allPermissionsAreGranted) {
                    discoverDevicesPageViewModel.startRescanning()
                }
            }
        ) {
            Text(
                text = stringResource(resource = Res.string.rescan_button_label),
                style = ComposeAppTheme.typography.buttonLabel,
                maxLines = 1
            )
        }
    }
}