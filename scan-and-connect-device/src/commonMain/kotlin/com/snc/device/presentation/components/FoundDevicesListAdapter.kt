package com.snc.device.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorProducer
import androidx.compose.ui.unit.dp
import com.compose.shared.extentions.deviceIconAndContainerModifier
import com.design.system.api.ComposeAppTheme
import com.platform.api.model.SwimConnectDevice
import com.snc.device.presentation.DiscoverDevicesPageViewModel
import com.snc.device.scan_and_connect_device.generated.resources.Res
import com.snc.device.scan_and_connect_device.generated.resources.connect_button_label
import com.snc.device.scan_and_connect_device.generated.resources.ic_device
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun FoundDeviceListAdapter(
    discoverDevicesPageViewModel: DiscoverDevicesPageViewModel,
    device: SwimConnectDevice,
    modifier: Modifier = Modifier
) {
    val buttonColor = ButtonDefaults.buttonColors(
        containerColor = ComposeAppTheme.colors.primaryColor,
        contentColor = Color.White
    )
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        val backgroundColor = ComposeAppTheme.colors.backgroundColor
        DeviceIconAndContainer(
            modifier = Modifier.deviceIconAndContainerModifier(backgroundColor = backgroundColor)
        )
        Spacer(modifier = Modifier.width(20.dp))
        DeviceNameAndAddress(device = device, modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.width(20.dp))
        Button(
            colors = buttonColor,
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            onClick = {
                discoverDevicesPageViewModel.connect(device = device)
            }
        ) {
            Text(
                text = stringResource(resource = Res.string.connect_button_label),
                maxLines = 1
            )
        }
    }
}

@Composable
private fun DeviceNameAndAddress(
    device: SwimConnectDevice,
    modifier: Modifier = Modifier
) {
    val primaryFontColor = ComposeAppTheme.colors.primaryFontColor
    val secondaryFontColor = ComposeAppTheme.colors.secondaryFontColor
    Column(modifier = modifier) {
        BasicText(
            text = device.name,
            maxLines = 1,
            style = ComposeAppTheme.typography.bodyLabelLarge,
            color = ColorProducer { primaryFontColor }
        )
        Spacer(modifier = Modifier.height(8.dp))
        BasicText(
            text = device.address,
            maxLines = 1,
            style = ComposeAppTheme.typography.bodyLabel,
            color = ColorProducer { secondaryFontColor }
        )
    }
}

@Composable
private fun DeviceIconAndContainer(modifier: Modifier = Modifier) {
    val deviceIcon = Res.drawable.ic_device
    Box(modifier = modifier) {
        Image(
            painter = painterResource(resource = deviceIcon),
            contentDescription = null,
            modifier = Modifier.height(20.dp)
        )
    }
}