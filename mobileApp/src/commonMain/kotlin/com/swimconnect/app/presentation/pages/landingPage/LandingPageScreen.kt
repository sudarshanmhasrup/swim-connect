package com.swimconnect.app.presentation.pages.landingPage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.compose.shared.components.HeadingAndMessage
import com.compose.shared.components.PrimaryButton
import com.compose.shared.extentions.landingPageHeadingAndMessageModifier
import com.compose.shared.extentions.letsConnectButtonAndBackgroundModifier
import com.compose.shared.routes.ComposeAppRoutes
import com.compose.shared.viewmodel.ComposeAppViewModel
import com.swimconnect.app.mobileapp.generated.resources.Res
import com.swimconnect.app.mobileapp.generated.resources.app_name
import com.swimconnect.app.mobileapp.generated.resources.ic_swimming
import com.swimconnect.app.mobileapp.generated.resources.landing_page_button_title
import com.swimconnect.app.mobileapp.generated.resources.landing_page_message
import org.jetbrains.compose.resources.painterResource

@Composable
fun LandingPageScreen(
    composeAppViewModel: ComposeAppViewModel,
    composeAppNavController: NavController,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        SwimmingIconAndContainer(modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(20.dp))
        HeadingAndMessage(
            heading = Res.string.app_name,
            message = Res.string.landing_page_message,
            modifier = Modifier.landingPageHeadingAndMessageModifier()
        )
        Spacer(modifier = Modifier.height(20.dp))
        LetsConnectButtonAndBackground(
            composeAppViewModel = composeAppViewModel,
            composeAppNavController = composeAppNavController,
            modifier = Modifier.letsConnectButtonAndBackgroundModifier()
        )
    }
}

@Composable
private fun SwimmingIconAndContainer(modifier: Modifier = Modifier) {
    val swimmingIcon = Res.drawable.ic_swimming
    Box(contentAlignment = Alignment.Center, modifier = modifier) {
        Image(
            painter = painterResource(resource = swimmingIcon),
            contentDescription = null
        )
    }
}

@Composable
private fun LetsConnectButtonAndBackground(
    composeAppViewModel: ComposeAppViewModel,
    composeAppNavController: NavController,
    modifier: Modifier = Modifier
) {
    Box(contentAlignment = Alignment.Center, modifier = modifier) {
        PrimaryButton(
            label = Res.string.landing_page_button_title,
            enabled = true,
            onClick = {
                composeAppViewModel.saveLandingPageAsViewed()
                composeAppNavController.navigate(ComposeAppRoutes.CHILD_DETAILS_PAGE) {
                    popUpTo(ComposeAppRoutes.LANDING_PAGE) {
                        inclusive = true
                    }
                }
            }
        )
    }
}