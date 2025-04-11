package com.swimconnect.app.presentation.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.child.details.presentation.ChildDetailsPage
import com.child.details.presentation.ChildDetailsPageViewModel
import com.compose.shared.viewmodel.ComposeAppViewModel
import com.compose.shared.routes.ComposeAppRoutes
import com.swimconnect.app.presentation.pages.finishSetupPage.FinishSetupPage
import com.swimconnect.app.presentation.pages.landingPage.LandingPageScreen

@Composable
fun ComposeAppNavigation(composeAppViewModel: ComposeAppViewModel) {
    val composeAppNavController = rememberNavController()
    val childDetailsPageViewModel: ChildDetailsPageViewModel = viewModel()

    val startDestination = when {
        composeAppViewModel.checkIfChildDetailsPageIsViewed() -> {
            ComposeAppRoutes.DISCOVER_DEVICES_PAGE
        }

        composeAppViewModel.checkIfLandingPageIsViewed() -> {
            ComposeAppRoutes.FINISH_SETUP_PAGE
        }

        else -> {
            ComposeAppRoutes.LANDING_PAGE
        }
    }

    NavHost(
        navController = composeAppNavController,
        startDestination = startDestination,
        enterTransition = {
            fadeIn(animationSpec = tween(0))
        },
        exitTransition = {
            fadeOut(animationSpec = tween(0))
        },
        popEnterTransition = {
            fadeIn(animationSpec = tween(0))
        },
        popExitTransition = {
            fadeOut(animationSpec = tween(0))
        }
    ) {
        composable(route = ComposeAppRoutes.LANDING_PAGE) {
            LandingPageScreen(
                composeAppViewModel = composeAppViewModel,
                composeAppNavController = composeAppNavController,
                modifier = Modifier.fillMaxSize()
            )
        }
        composable(route = ComposeAppRoutes.FINISH_SETUP_PAGE) {
            FinishSetupPage(
                composeAppNavController = composeAppNavController,
                modifier = Modifier.fillMaxSize()
            )
        }
        composable(route = ComposeAppRoutes.CHILD_DETAILS_PAGE) {
            ChildDetailsPage(
                composeAppViewModel = composeAppViewModel,
                childDetailsPageViewModel = childDetailsPageViewModel,
                composeAppNavController = composeAppNavController,
                modifier = Modifier.fillMaxSize()
            )
        }
        composable(route = ComposeAppRoutes.DISCOVER_DEVICES_PAGE) {

        }
    }
}