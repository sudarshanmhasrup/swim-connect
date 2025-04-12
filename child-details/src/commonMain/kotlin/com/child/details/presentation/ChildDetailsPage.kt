package com.child.details.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.child.details.child_details.generated.resources.Res
import com.child.details.child_details.generated.resources.child_details_page_heading
import com.child.details.child_details.generated.resources.child_details_page_message
import com.child.details.child_details.generated.resources.confirm_details_page_heading
import com.child.details.child_details.generated.resources.confirm_details_page_message
import com.child.details.presentation.components.BackButtonContainer
import com.child.details.presentation.components.ChildDetailsPageHeadingAndMessage
import com.child.details.presentation.navigation.ChildDetailsPageNavigation
import com.compose.shared.extentions.backButtonContainerModifier
import com.compose.shared.extentions.childDetailsPageHeadingAndMessageModifier
import com.compose.shared.presentation.ComposeAppViewModel

@Composable
fun ChildDetailsPage(
    composeAppViewModel: ComposeAppViewModel,
    childDetailsPageViewModel: ChildDetailsPageViewModel,
    composeAppNavController: NavController,
    modifier: Modifier = Modifier
) {
    val childDetailsUiState = childDetailsPageViewModel.childDetailsUiState.collectAsState()

    val childDetailsPageNavController = rememberNavController()

    val pageHeading = if (childDetailsUiState.value.isConfirmationPage) {
        Res.string.confirm_details_page_heading
    } else {
        Res.string.child_details_page_heading
    }
    val pageMessage = if (childDetailsUiState.value.isConfirmationPage) {
        Res.string.confirm_details_page_message
    } else {
        Res.string.child_details_page_message
    }

    Box(modifier = modifier) {
        Column(modifier = Modifier.fillMaxSize()) {
            ChildDetailsPageHeadingAndMessage(
                heading = pageHeading,
                message = pageMessage,
                modifier = Modifier.childDetailsPageHeadingAndMessageModifier()
            )
            ChildDetailsPageNavigation(
                composeAppViewModel = composeAppViewModel,
                childDetailsPageViewModel = childDetailsPageViewModel,
                composeAppNavController = composeAppNavController,
                childDetailsPageNavController = childDetailsPageNavController
            )
        }
        if (childDetailsUiState.value.showBackButtonContainer) {
            BackButtonContainer(
                modifier = Modifier.backButtonContainerModifier(),
                onBackButtonClicked = {
                    childDetailsPageNavController.popBackStack()
                }
            )
        }
    }
}