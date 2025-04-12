package com.child.details.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.child.details.child_details.generated.resources.Res
import com.child.details.child_details.generated.resources.mode_of_alert_input_placeholder
import com.child.details.child_details.generated.resources.next_button_label
import com.child.details.child_details.generated.resources.save_button_label
import com.child.details.presentation.ChildDetailsPageViewModel
import com.child.details.presentation.components.PrimaryOptionSelection
import com.child.details.presentation.navigation.ChildDetailsPageRoutes
import com.compose.shared.presentation.components.PrimaryButton

@Composable
fun ModeOfAlertInputUi(
    childDetailsPageViewModel: ChildDetailsPageViewModel,
    childDetailsPageNavController: NavController,
    modifier: Modifier = Modifier
) {
    val childDetailsUiState = childDetailsPageViewModel.childDetailsUiState.collectAsState()

    val primaryButtonLabel = if (childDetailsUiState.value.isEditingInformation) {
        Res.string.save_button_label
    } else {
        Res.string.next_button_label
    }

    val previousPage = childDetailsPageNavController.previousBackStackEntry?.destination?.route
    val page1 = ChildDetailsPageRoutes.CUSTOM_ALERT_TIME_INPUT_UI
    val page2 = ChildDetailsPageRoutes.ALERT_TIME_INPUT_UI
    if (previousPage == page1 || previousPage == page2) {
        childDetailsPageViewModel.showBackButtonContainer()
        childDetailsPageViewModel.turnOffEditingMode()
        childDetailsPageViewModel.hideConfirmationPageHeading()
    }

    Column(modifier = modifier) {
        // Child swimming skill level option input
        PrimaryOptionSelection(
            placeholder = Res.string.mode_of_alert_input_placeholder,
            optionList = childDetailsPageViewModel.getModeOfAlertOptions(),
            currentSelectedOption = childDetailsUiState.value.modeOfAlert.toString(),
            onOptionSelectionChanged = {
                childDetailsPageViewModel.updateModeOfAlertOption(value = it)
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(20.dp))
        // Button to jump to the alert time input component
        PrimaryButton(
            label = primaryButtonLabel,
            enabled = childDetailsUiState.value.enableSixthContinueButton,
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                primaryButtonClickEvent(
                    childDetailsPageViewModel,
                    childDetailsPageNavController,
                    childDetailsUiState.value.isEditingInformation,
                    childDetailsUiState.value.enableSixthContinueButton
                )
            })
    }
}

private val primaryButtonClickEvent: (
    childDetailsPageViewModel: ChildDetailsPageViewModel,
    childDetailsPageNavController: NavController,
    isEditingInformation: Boolean,
    enableSixthContinue: Boolean
) -> Unit =
    { childDetailsPageViewModel, childDetailsPageNavController, isEditingInformation, enableSixthContinueButton ->
        childDetailsPageViewModel.saveModeOfAlert()
        childDetailsPageViewModel.hideBackButtonContainer()
        when {
            enableSixthContinueButton && !isEditingInformation -> {
                childDetailsPageNavController.navigate(
                    route = ChildDetailsPageRoutes.CONFIRM_DETAILS_UI
                )
            }

            enableSixthContinueButton && isEditingInformation -> {
                childDetailsPageNavController.popBackStack()
            }
        }
    }