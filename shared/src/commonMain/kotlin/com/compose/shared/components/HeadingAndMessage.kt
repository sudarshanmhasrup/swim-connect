package com.compose.shared.components

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
    modifier: Modifier = Modifier
) {
    val primaryFontColor = ComposeAppTheme.colors.primaryFontColor
    val secondaryFontColor = ComposeAppTheme.colors.secondaryFontColor
    Column(modifier = modifier) {
        BasicText(
            text = stringResource(resource = heading),
            style = ComposeAppTheme.typography.bodyHeadline.copy(textAlign = TextAlign.Center),
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