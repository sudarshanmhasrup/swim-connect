package com.swimconnect.app.theme

import androidx.compose.runtime.Composable
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
    primaryDisabledColor = primaryDisabledColorLight,
    inputErrorColor = inputErrorColor
)

val darkColorScheme = Colors(
    backgroundColor = backgroundColorDark,
    primaryColor = primaryColor,
    secondaryColor = secondaryColorDark,
    primaryFontColor = primaryTextColorDark,
    secondaryFontColor = secondaryTextColorDark,
    borderColor = borderColorDark,
    inputHintColor = inputHintColorDark,
    primaryDisabledColor = primaryDisabledColorDark,
    inputErrorColor = inputErrorColor
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