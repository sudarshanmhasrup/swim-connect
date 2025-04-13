package com.swimconnect.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.systemBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.compose.shared.extentions.backgroundContainerModifier
import com.compose.shared.presentation.ComposeAppViewModel
import com.design.system.api.ComposeAppTheme
import com.swimconnect.app.presentation.navigation.ComposeAppNavigation
import com.swimconnect.app.theme.SwimConnectTheme

@Composable
fun ComposeApp(isDarkMode: Boolean = false) {
    val composeAppViewModel: ComposeAppViewModel = viewModel()
    SwimConnectTheme(isDarkMode = isDarkMode) {
        val systemBarBackgroundColor = if (isDarkMode) Color(0xFF101418) else Color(0xFFFFFFFF)
        val backgroundColor = ComposeAppTheme.colors.backgroundColor

        val density = LocalDensity.current
        val insets = WindowInsets.systemBars

        Box(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.backgroundContainerModifier(backgroundColor = backgroundColor)) {
                ComposeAppNavigation(composeAppViewModel = composeAppViewModel)
            }
            // Status bar
            SystemBarBackground(
                backgroundColor = systemBarBackgroundColor,
                height = with(density) { insets.getTop(this).toDp() },
                contentAlignment = Alignment.TopCenter,
                modifier = Modifier.fillMaxWidth()
            )
            // Navigation bar
            SystemBarBackground(
                backgroundColor = systemBarBackgroundColor,
                height = with(density) { insets.getBottom(this).toDp() },
                contentAlignment = Alignment.BottomCenter,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}


@Composable
private fun SystemBarBackground(
    backgroundColor: Color,
    height: Dp,
    contentAlignment: Alignment,
    modifier: Modifier = Modifier
) {
    Box(contentAlignment = contentAlignment, modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height)
                .alpha(0.80f)
                .background(color = backgroundColor)
        )
    }
}