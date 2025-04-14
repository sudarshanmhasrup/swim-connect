package com.snc.device.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.compose.shared.extentions.commonUiButtonAndBackgroundModifier
import com.compose.shared.extentions.commonUiHeadingModifier
import com.compose.shared.extentions.uiIconBoxModifier
import com.compose.shared.presentation.components.HeadingAndMessage
import com.compose.shared.presentation.components.PrimaryButton
import com.design.system.api.ComposeAppTheme
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun CommonUi(
    heading: StringResource,
    message: StringResource,
    uiIcon: DrawableResource,
    buttonLabel: StringResource,
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        UiIcon(uiIcon = uiIcon, modifier = Modifier.fillMaxWidth())
        HeadingAndMessage(
            heading = heading,
            message = message,
            smallHeading = true,
            modifier = Modifier.commonUiHeadingModifier()
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.commonUiButtonAndBackgroundModifier()
        ) {
            PrimaryButton(label = buttonLabel, enabled = true, onClick = onButtonClick)
        }
    }
}

@Composable
private fun UiIcon(
    uiIcon: DrawableResource,
    modifier: Modifier
) {
    val secondaryColor = ComposeAppTheme.colors.secondaryColor
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.uiIconBoxModifier(backgroundColor = secondaryColor)
        ) {
            Image(
                painter = painterResource(resource = uiIcon),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.height(40.dp)
            )
        }
    }
}