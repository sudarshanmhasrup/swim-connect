package com.snc.device.presentation.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorProducer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.compose.shared.extentions.dialogBoxDoneButtonAndContainer
import com.compose.shared.extentions.dialogBoxUiModifier
import com.compose.shared.extentions.dialogueBoxHeadingAndMessageModifier
import com.compose.shared.extentions.dialogueBoxImageAndContainerModifier
import com.compose.shared.extentions.permissionImageModifier
import com.compose.shared.extentions.permissionListAdapterModifier
import com.compose.shared.extentions.permissionsListModifier
import com.compose.shared.presentation.components.HeadingAndMessage
import com.design.system.api.ComposeAppTheme
import com.snc.device.presentation.DiscoverDevicesPageViewModel
import com.snc.device.scan_and_connect_device.generated.resources.Res
import com.snc.device.scan_and_connect_device.generated.resources.allow_button_label
import com.snc.device.scan_and_connect_device.generated.resources.allowed_button_label
import com.snc.device.scan_and_connect_device.generated.resources.done_button_label
import com.snc.device.scan_and_connect_device.generated.resources.missing_permissions_dialogue_heading
import com.snc.device.scan_and_connect_device.generated.resources.missing_permissions_dialogue_message
import com.snc.device.scan_and_connect_device.generated.resources.missing_permissions_image
import com.snc.device.scan_and_connect_device.generated.resources.permissions_granted_dialogue_heading
import com.snc.device.scan_and_connect_device.generated.resources.permissions_granted_dialogue_message
import com.snc.device.scan_and_connect_device.generated.resources.permissions_granted_image
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun MissingPermissionsUi(
    discoverDevicesPageViewModel: DiscoverDevicesPageViewModel,
    modifier: Modifier = Modifier
) {
    Column(verticalArrangement = Arrangement.Bottom, modifier = modifier) {
        DialogBoxUi(
            discoverDevicesPageViewModel = discoverDevicesPageViewModel,
            modifier = Modifier.dialogBoxUiModifier(rememberScrollState())
        )
    }
}

@Composable
private fun DialogBoxUi(
    discoverDevicesPageViewModel: DiscoverDevicesPageViewModel,
    modifier: Modifier = Modifier
) {
    val discoverDevicesPageUiState =
        discoverDevicesPageViewModel.discoverDevicesPageUiState.collectAsState()

    val heading = if (discoverDevicesPageUiState.value.mainPermissionAreGranted) {
        Res.string.permissions_granted_dialogue_heading
    } else {
        Res.string.missing_permissions_dialogue_heading
    }
    val message = if (discoverDevicesPageUiState.value.mainPermissionAreGranted) {
        Res.string.permissions_granted_dialogue_message
    } else {
        Res.string.missing_permissions_dialogue_message
    }

    val image = if (discoverDevicesPageUiState.value.mainPermissionAreGranted) {
        Res.drawable.permissions_granted_image
    } else {
        Res.drawable.missing_permissions_image
    }

    Column(modifier = modifier) {
        DoneButtonAndContainer(
            enableDoneButton = discoverDevicesPageUiState.value.mainPermissionAreGranted,
            modifier = Modifier.dialogBoxDoneButtonAndContainer(),
            onClick = {
                discoverDevicesPageViewModel.requestBackgroundPermissions()
            }
        )
        DialogueBoxHeadingWithImage(
            image = image,
            contentDescription = heading,
            heading = heading,
            message = message,
            modifier = Modifier.fillMaxWidth()
        )
        PermissionsList(
            discoverDevicesPageViewModel = discoverDevicesPageViewModel,
            modifier = Modifier.permissionsListModifier(rememberScrollState())
        )
    }
}

@Composable
private fun DoneButtonAndContainer(
    enableDoneButton: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val buttonColors = ButtonDefaults.buttonColors(
        containerColor = Color.Transparent,
        contentColor = ComposeAppTheme.colors.primaryColor,
        disabledContainerColor = Color.Transparent,
        disabledContentColor = ComposeAppTheme.colors.primaryDisabledColor
    )
    Box(contentAlignment = Alignment.CenterEnd, modifier = modifier) {
        Button(
            colors = buttonColors,
            enabled = enableDoneButton,
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp),
            onClick = onClick
        ) {
            Text(
                text = stringResource(resource = Res.string.done_button_label),
                maxLines = 1,
                style = ComposeAppTheme.typography.buttonLabel
            )
        }
    }
}

@Composable
private fun DialogueBoxHeadingWithImage(
    image: DrawableResource,
    contentDescription: StringResource,
    heading: StringResource,
    message: StringResource,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        DialogueBoxImageAndContainer(
            image = image,
            contentDescription = contentDescription,
            modifier = Modifier.dialogueBoxImageAndContainerModifier()
        )
        HeadingAndMessage(
            heading = heading,
            message = message,
            modifier = Modifier.dialogueBoxHeadingAndMessageModifier()
        )
    }
}

@Composable
private fun DialogueBoxImageAndContainer(
    image: DrawableResource,
    contentDescription: StringResource,
    modifier: Modifier = Modifier
) {
    Box(contentAlignment = Alignment.Center, modifier = modifier) {
        Image(
            painter = painterResource(resource = image),
            contentDescription = stringResource(resource = contentDescription)
        )
    }
}


@Composable
private fun PermissionsList(
    discoverDevicesPageViewModel: DiscoverDevicesPageViewModel,
    modifier: Modifier = Modifier
) {
    val discoverDevicesPageUiState =
        discoverDevicesPageViewModel.discoverDevicesPageUiState.collectAsState()
    val runtimePermissionsList = discoverDevicesPageUiState.value.runtimePermissionsList
    Column(modifier = modifier) {
        runtimePermissionsList.forEachIndexed { index, permission ->
            if (permission.showOnUi) {
                PermissionListAdapter(
                    image = permission.image!!,
                    label = permission.label!!,
                    description = permission.description!!,
                    isPermissionGranted = permission.isPermissionGranted,
                    modifier = Modifier.permissionListAdapterModifier(),
                    onClick = {
                        discoverDevicesPageViewModel.requestRuntimePermission(
                            runtimePermission = permission
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun PermissionListAdapter(
    isPermissionGranted: Boolean,
    image: DrawableResource,
    label: StringResource,
    description: StringResource,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val primaryFontColor = ComposeAppTheme.colors.primaryFontColor
    val secondaryFontColor = ComposeAppTheme.colors.secondaryFontColor

    val buttonTitle = if (isPermissionGranted) {
        Res.string.allowed_button_label
    } else {
        Res.string.allow_button_label
    }

    val buttonColorWhenPermissionGranted = ButtonDefaults.outlinedButtonColors(
        contentColor = Color.White,
        containerColor = ComposeAppTheme.colors.primaryColor,
        disabledContentColor = Color.White,
        disabledContainerColor = ComposeAppTheme.colors.primaryColor
    )
    val buttonColorWhenPermissionDenied = ButtonDefaults.outlinedButtonColors(
        contentColor = ComposeAppTheme.colors.primaryColor,
        containerColor = Color.Transparent
    )
    val buttonColors = if (isPermissionGranted) {
        buttonColorWhenPermissionGranted
    } else {
        buttonColorWhenPermissionDenied
    }
    val borderStroke = if (isPermissionGranted) {
        BorderStroke(
            width = 1.dp,
            color = Color.Transparent
        )
    } else {
        BorderStroke(
            width = 1.dp,
            color = ComposeAppTheme.colors.primaryColor
        )
    }

    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
        Image(
            painter = painterResource(resource = image),
            modifier = Modifier.permissionImageModifier(),
            contentScale = ContentScale.Crop,
            contentDescription = stringResource(resource = label)
        )
        Spacer(modifier = Modifier.width(20.dp))
        Column(modifier = Modifier.weight(1f)) {
            BasicText(
                text = stringResource(resource = label),
                maxLines = 1,
                color = ColorProducer { primaryFontColor },
                style = ComposeAppTheme.typography.bodyHeadlineSmall
            )
            Spacer(modifier = Modifier.height(8.dp))
            BasicText(
                text = stringResource(resource = description),
                maxLines = 4,
                color = ColorProducer { secondaryFontColor },
                style = ComposeAppTheme.typography.bodyLabel
            )
        }
        Spacer(modifier = Modifier.width(20.dp))
        OutlinedButton(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
            colors = buttonColors,
            enabled = !isPermissionGranted,
            border = borderStroke,
            onClick = onClick
        ) {
            Text(
                text = stringResource(resource = buttonTitle),
                maxLines = 1,
                style = ComposeAppTheme.typography.buttonLabelSmall
            )
        }
    }
}