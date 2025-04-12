package com.child.details.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.child.details.child_details.generated.resources.Res
import com.child.details.child_details.generated.resources.custom_alert_time_input_error
import com.child.details.child_details.generated.resources.custom_alert_time_input_hint
import com.child.details.child_details.generated.resources.custom_alert_time_input_placeholder
import com.child.details.child_details.generated.resources.next_button_label
import com.child.details.child_details.generated.resources.save_button_label
import com.child.details.presentation.ChildDetailsPageViewModel
import com.child.details.presentation.navigation.ChildDetailsPageRoutes
import com.compose.shared.presentation.components.PrimaryButton
import com.compose.shared.presentation.components.PrimaryUserInputField
import com.compose.shared.extentions.childAgeInputKeyboardOptions
import com.compose.shared.util.KeyboardFocusRequestManager

@Composable
fun CustomAlertTimeInputUi(
    childDetailsPageViewModel: ChildDetailsPageViewModel,
    childDetailsPageNavController: NavController,
    modifier: Modifier = Modifier
) {
    val childDetailsUiState = childDetailsPageViewModel.childDetailsUiState.collectAsState()

    val primaryButtonClickEvent: () -> Unit = {
        getPrimaryButtonClickEvent(
            childDetailsPageViewModel,
            childDetailsPageNavController,
            childDetailsUiState.value.isEditingInformation,
            childDetailsUiState.value.enableFifthContinueButton
        )
    }

    val keyboardActions = KeyboardActions(
        onNext = {
            primaryButtonClickEvent()
        }
    )

    val primaryButtonLabel = if (childDetailsUiState.value.isEditingInformation) {
        Res.string.save_button_label
    } else {
        Res.string.next_button_label
    }

    Column(modifier = modifier) {
        // Custom alert time input component
        PrimaryUserInputField(
            value = childDetailsUiState.value.customAlertTime ?: "",
            placeholder = Res.string.custom_alert_time_input_placeholder,
            hint = Res.string.custom_alert_time_input_hint,
            inputErrorMessage = Res.string.custom_alert_time_input_error,
            showErrorMessage = childDetailsUiState.value.showCustomAlertTimeInputError,
            keyboardOptions = KeyboardOptions.Default.childAgeInputKeyboardOptions(),
            keyboardActions = keyboardActions,
            focusRequester = KeyboardFocusRequestManager.getFocusRequester(),
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { childDetailsPageViewModel.updateCustomAlertTime(value = it) }
        )
        Spacer(modifier = Modifier.height(20.dp))
        // Button to jump to the child skill level input component
        PrimaryButton(
            label = primaryButtonLabel,
            enabled = childDetailsUiState.value.enableFifthContinueButton,
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                primaryButtonClickEvent()
            }
        )
    }
}

private val getPrimaryButtonClickEvent: (
    childDetailsPageViewModel: ChildDetailsPageViewModel,
    childDetailsPageNavController: NavController,
    isEditingInformation: Boolean,
    enableFifthContinueButton: Boolean
) -> Unit =
    { childDetailsPageViewModel, childDetailsPageNavController, isEditingInformation, enableFifthContinueButton ->
        childDetailsPageViewModel.saveCustomAlertTime()
        when {
            enableFifthContinueButton && !isEditingInformation -> {
                childDetailsPageNavController.navigate(
                    route = ChildDetailsPageRoutes.MODE_OF_ALERT_INPUT_UI
                )
            }

            enableFifthContinueButton && isEditingInformation -> {
                childDetailsPageNavController.popBackStack()
                childDetailsPageNavController.popBackStack()
            }
        }
    }