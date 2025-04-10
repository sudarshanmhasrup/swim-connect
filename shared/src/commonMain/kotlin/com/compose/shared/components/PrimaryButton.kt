package com.compose.shared.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.design.system.api.ComposeAppTheme
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

// Primary button to be used across all pages.
@Composable
fun PrimaryButton(
    label: StringResource,
    modifier: Modifier = Modifier,
    enabled: Boolean = false,
    onClick: () -> Unit
) {
    val buttonColors = ButtonDefaults.buttonColors(
        containerColor = ComposeAppTheme.colors.primaryColor,
        disabledContainerColor = ComposeAppTheme.colors.primaryDisabledColor,
    )

    Button(
        onClick = onClick,
        shape = RoundedCornerShape(1000.dp),
        colors = buttonColors,
        enabled = enabled,
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 20.dp),
        modifier = modifier
    ) {
        Text(
            text = stringResource(resource = label),
            color = Color.White,
            maxLines = 1,
            style = ComposeAppTheme.typography.buttonLabel
        )
    }
}