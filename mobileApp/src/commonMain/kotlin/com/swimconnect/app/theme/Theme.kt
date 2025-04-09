package com.swimconnect.app.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.design.system.api.ComposeAppTheme
import com.design.system.colors.Colors

val lightColorScheme = Colors(
    backgroundColor = backgroundColorLight,
    primaryColor = primaryColor,
    secondaryColor = secondaryColorLight,
    primaryFontColor = primaryTextColorLight,
    secondaryFontColor = secondaryTextColorLight,
    borderColor = borderColorLight,
    inputHintColor = inputHintColorLight,
    primaryDisabledColor = primaryDisabledColor,
    inputErrorColor = inputErrorColorLight
)

val darkColorScheme = Colors(
    backgroundColor = Color.Black
)

@Composable
fun SwimConnectTheme(
    isDarkMode: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (isDarkMode) darkColorScheme else lightColorScheme
    ComposeAppTheme(
        typography = getTypography(),
        colors = colorScheme,
        content = content
    )
}