package com.swimconnect.app

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.compose.shared.extentions.backgroundContainerModifier
import com.compose.shared.viewmodel.ComposeAppViewModel
import com.design.system.api.ComposeAppTheme
import com.swimconnect.app.presentation.navigation.ComposeAppNavigation
import com.swimconnect.app.theme.SwimConnectTheme

@Composable
fun ComposeApp(isDarkMode: Boolean = false) {
    val composeAppViewModel: ComposeAppViewModel = viewModel()
    SwimConnectTheme(isDarkMode = isDarkMode) {
        val backgroundColor = ComposeAppTheme.colors.backgroundColor
        Column(modifier = Modifier.backgroundContainerModifier(color = backgroundColor)) {
            ComposeAppNavigation(composeAppViewModel = composeAppViewModel)
        }
    }
}