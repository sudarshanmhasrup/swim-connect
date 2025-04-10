package com.swimconnect.app.presentation.pages.finishSetupPage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.compose.shared.components.HeadingAndMessage
import com.compose.shared.components.PrimaryButton
import com.compose.shared.extentions.finishAccountSetupPageHeadingAndMessageModifier
import com.compose.shared.extentions.finishSetupPagePrimaryButtonAndContainerModifier
import com.swimconnect.app.mobileapp.generated.resources.Res
import com.swimconnect.app.mobileapp.generated.resources.finish_setup_button_title
import com.swimconnect.app.mobileapp.generated.resources.finish_setup_page_heading
import com.swimconnect.app.mobileapp.generated.resources.finish_setup_page_message
import com.swimconnect.app.mobileapp.generated.resources.incomplete_setup_image
import com.swimconnect.mobile.presentation.navigation.routes.ComposeAppRoutes
import org.jetbrains.compose.resources.painterResource

@Composable
fun FinishSetupPage(
    composeAppNavController: NavController,
    modifier: Modifier = Modifier
) {
    val incompleteSetupImage = Res.drawable.incomplete_setup_image
    Column(verticalArrangement = Arrangement.Center, modifier = modifier) {
        Image(
            painter = painterResource(resource = incompleteSetupImage),
            contentDescription = null,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        HeadingAndMessage(
            heading = Res.string.finish_setup_page_heading,
            message = Res.string.finish_setup_page_message,
            modifier = Modifier.finishAccountSetupPageHeadingAndMessageModifier()
        )
        FinishSetupButtonAndContainer(
            composeAppNavController = composeAppNavController,
            modifier = Modifier.finishSetupPagePrimaryButtonAndContainerModifier()
        )
    }
}

@Composable
private fun FinishSetupButtonAndContainer(
    composeAppNavController: NavController,
    modifier: Modifier = Modifier
) {
    Box(contentAlignment = Alignment.Center, modifier = modifier) {
        // Completed account setup button
        PrimaryButton(
            label = Res.string.finish_setup_button_title,
            enabled = true,
            onClick = {
                composeAppNavController.navigate(ComposeAppRoutes.CHILD_DETAILS_PAGE)
            }
        )
    }
}