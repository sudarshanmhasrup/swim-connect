package com.child.details.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
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
import com.compose.shared.extentions.childDetailsPageHeadingModifier

@Composable
fun ChildDetailsPage(
    composeAppNavController: NavController,
    modifier: Modifier = Modifier
) {
    val childDetailsPageViewModel: ChildDetailsPageViewModel = viewModel()
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

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = modifier.verticalScroll(state = rememberScrollState())) {
            ChildDetailsPageHeadingAndMessage(
                heading = pageHeading,
                message = pageMessage,
                modifier = Modifier.childDetailsPageHeadingModifier()
            )
            ChildDetailsPageNavigation(
                childDetailsPageViewModel = childDetailsPageViewModel,
                composeAppNavController = composeAppNavController,
                childDetailsPageNavController = childDetailsPageNavController
            )
        }
        AnimatedVisibility(
            childDetailsUiState.value.showBackButtonContainer,
            enter = fadeIn(animationSpec = tween(0)),
            exit = fadeOut(animationSpec = tween(0))
        ) {
            BackButtonContainer(
                modifier = Modifier.backButtonContainerModifier(),
                onBackButtonClicked = {
                    childDetailsPageNavController.popBackStack()
                }
            )
        }
    }
}