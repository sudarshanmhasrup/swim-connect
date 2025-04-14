package com.compose.shared.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorProducer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.design.system.api.ComposeAppTheme
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

// Common heading component used across key pages.
@Composable
fun HeadingAndMessage(
    heading: StringResource,
    message: StringResource,
    smallHeading: Boolean = false,
    modifier: Modifier = Modifier
) {
    val primaryFontColor = ComposeAppTheme.colors.primaryFontColor
    val secondaryFontColor = ComposeAppTheme.colors.secondaryFontColor

    val headingStyle = if (smallHeading) {
        ComposeAppTheme.typography.bodyHeadlineMedium.copy(textAlign = TextAlign.Center)
    } else {
        ComposeAppTheme.typography.bodyHeadline.copy(textAlign = TextAlign.Center)
    }

    Column(modifier = modifier) {
        BasicText(
            text = stringResource(resource = heading),
            style = headingStyle,
            color = ColorProducer { primaryFontColor },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        BasicText(
            text = stringResource(resource = message),
            style = ComposeAppTheme.typography.bodyMessage.copy(textAlign = TextAlign.Center),
            color = ColorProducer { secondaryFontColor },
            modifier = Modifier.fillMaxWidth()
        )
    }
}