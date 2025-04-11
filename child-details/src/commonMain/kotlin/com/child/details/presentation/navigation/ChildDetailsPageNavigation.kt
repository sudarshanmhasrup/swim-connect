package com.child.details.presentation.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.child.details.presentation.ChildDetailsPageViewModel
import com.child.details.presentation.ui.AlertTimeInputUi
import com.child.details.presentation.ui.ChildAgeInputUi
import com.child.details.presentation.ui.ChildNameInputUi
import com.child.details.presentation.ui.ChildSwimmingSkillLevelInputUi
import com.child.details.presentation.ui.ConfirmDetailsUi
import com.child.details.presentation.ui.CustomAlertTimeInputUi
import com.child.details.presentation.ui.ModeOfAlertInputUi
import com.compose.shared.extentions.childDetailsPageInputUiModifier
import com.compose.shared.extentions.confirmChildDetailsUiModifier
import com.compose.shared.viewmodel.ComposeAppViewModel

@Composable
fun ChildDetailsPageNavigation(
    composeAppViewModel: ComposeAppViewModel,
    childDetailsPageViewModel: ChildDetailsPageViewModel,
    composeAppNavController: NavController,
    childDetailsPageNavController: NavHostController
) {
    val childDetailsPageUiState = childDetailsPageViewModel.childDetailsUiState.value

    val startDestination = if (childDetailsPageUiState.modeOfAlert == null) {
        ChildDetailsPageRoutes.CHILD_NAME_INPUT_UI
    } else {
        ChildDetailsPageRoutes.CONFIRM_DETAILS_UI
    }

    childDetailsPageViewModel.hideBackButtonContainer()

    NavHost(
        navController = childDetailsPageNavController,
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
        composable(route = ChildDetailsPageRoutes.CHILD_NAME_INPUT_UI) {
            ChildNameInputUi(
                childDetailsPageViewModel = childDetailsPageViewModel,
                childDetailsPageNavController = childDetailsPageNavController,
                modifier = Modifier.childDetailsPageInputUiModifier()
            )
        }
        composable(route = ChildDetailsPageRoutes.CHILD_AGE_INPUT_UI) {
            ChildAgeInputUi(
                childDetailsPageViewModel = childDetailsPageViewModel,
                childDetailsPageNavController = childDetailsPageNavController,
                modifier = Modifier.childDetailsPageInputUiModifier()
            )
        }
        composable(route = ChildDetailsPageRoutes.CHILD_SWIMMING_SKILL_LEVEL_UI) {
            ChildSwimmingSkillLevelInputUi(
                childDetailsPageViewModel = childDetailsPageViewModel,
                childDetailsPageNavController = childDetailsPageNavController,
                modifier = Modifier.childDetailsPageInputUiModifier()
            )
        }
        composable(route = ChildDetailsPageRoutes.ALERT_TIME_INPUT_UI) {
            AlertTimeInputUi(
                childDetailsPageViewModel = childDetailsPageViewModel,
                childDetailsPageNavController = childDetailsPageNavController,
                modifier = Modifier.childDetailsPageInputUiModifier()
            )
        }
        composable(route = ChildDetailsPageRoutes.CUSTOM_ALERT_TIME_INPUT_UI) {
            CustomAlertTimeInputUi(
                childDetailsPageViewModel = childDetailsPageViewModel,
                childDetailsPageNavController = childDetailsPageNavController,
                modifier = Modifier.childDetailsPageInputUiModifier()
            )
        }
        composable(route = ChildDetailsPageRoutes.MODE_OF_ALERT_INPUT_UI) {
            ModeOfAlertInputUi(
                childDetailsPageViewModel = childDetailsPageViewModel,
                childDetailsPageNavController = childDetailsPageNavController,
                modifier = Modifier.childDetailsPageInputUiModifier()
            )
        }
        composable(route = ChildDetailsPageRoutes.CONFIRM_DETAILS_UI) {
            ConfirmDetailsUi(
                composeAppViewModel = composeAppViewModel,
                childDetailsPageViewModel = childDetailsPageViewModel,
                composeAppNavController = composeAppNavController,
                childDetailsPageNavController = childDetailsPageNavController,
                modifier = Modifier.confirmChildDetailsUiModifier()
            )
        }
    }
}