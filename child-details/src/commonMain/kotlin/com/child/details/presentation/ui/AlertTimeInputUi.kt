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
import com.child.details.child_details.generated.resources.alert_time_input_placeholder
import com.child.details.child_details.generated.resources.next_button_label
import com.child.details.child_details.generated.resources.save_button_label
import com.child.details.model.AlertTime
import com.child.details.presentation.ChildDetailsPageViewModel
import com.child.details.presentation.components.PrimaryOptionSelection
import com.child.details.presentation.navigation.ChildDetailsPageRoutes
import com.compose.shared.components.PrimaryButton

@Composable
fun AlertTimeInputUi(
    childDetailsPageViewModel: ChildDetailsPageViewModel,
    childDetailsPageNavController: NavController,
    modifier: Modifier = Modifier
) {
    val childDetailsUiState = childDetailsPageViewModel.childDetailsUiState.collectAsState()

    val primaryButtonLabel =
        if (childDetailsUiState.value.isEditingInformation && childDetailsUiState.value.alertTime != AlertTime.CUSTOM) {
            Res.string.save_button_label
        } else {
            Res.string.next_button_label
        }

    Column(modifier = modifier) {
        // Alert time option input
        PrimaryOptionSelection(
            placeholder = Res.string.alert_time_input_placeholder,
            optionList = childDetailsPageViewModel.getCustomAlterTimeOptions(),
            currentSelectedOption = childDetailsUiState.value.alertTime.toString(),
            onOptionSelectionChanged = {
                childDetailsPageViewModel.updateAlertTimeOption(value = it)
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(20.dp))
        // Button to jump to the custom alert time input component
        PrimaryButton(
            label = primaryButtonLabel,
            enabled = childDetailsUiState.value.enableFourthContinueButton,
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                primaryButtonClickHandler(
                    childDetailsPageViewModel,
                    childDetailsPageNavController,
                    childDetailsUiState.value.alertTime,
                    childDetailsUiState.value.enableFourthContinueButton,
                    childDetailsUiState.value.isEditingInformation
                )
            }
        )
    }
}

private val primaryButtonClickHandler: (
    childDetailsPageViewModel: ChildDetailsPageViewModel,
    childDetailsPageNavController: NavController,
    alertTime: AlertTime?,
    enableFourthContinueButton: Boolean,
    isEditingInformation: Boolean
) -> Unit =
    { childDetailsPageViewModel, childDetailsPageNavController, alertTime, enableFourthContinueButton, isEditingInformation ->
        childDetailsPageViewModel.saveAlertTime()
        when {
            enableFourthContinueButton && !isEditingInformation && alertTime != AlertTime.CUSTOM -> {
                childDetailsPageNavController.navigate(
                    route = ChildDetailsPageRoutes.MODE_OF_ALERT_INPUT_UI
                )
            }

            enableFourthContinueButton && !isEditingInformation && alertTime == AlertTime.CUSTOM -> {
                childDetailsPageNavController.navigate(
                    route = ChildDetailsPageRoutes.CUSTOM_ALERT_TIME_INPUT_UI
                )
            }

            enableFourthContinueButton && isEditingInformation && alertTime != AlertTime.CUSTOM -> {
                childDetailsPageNavController.popBackStack()
            }

            enableFourthContinueButton && isEditingInformation && alertTime == AlertTime.CUSTOM -> {
                childDetailsPageNavController.navigate(
                    route = ChildDetailsPageRoutes.CUSTOM_ALERT_TIME_INPUT_UI
                )
            }
        }
    }