package com.swimconnect.app

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable

@Composable
fun AndroidApp(isDarkMode: Boolean = isSystemInDarkTheme()) {
    ComposeApp(isDarkMode = isDarkMode)
}