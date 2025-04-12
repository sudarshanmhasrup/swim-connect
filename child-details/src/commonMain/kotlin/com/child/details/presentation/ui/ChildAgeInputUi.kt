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
import com.child.details.child_details.generated.resources.child_age_input_error
import com.child.details.child_details.generated.resources.child_age_input_hint
import com.child.details.child_details.generated.resources.child_age_input_placeholder
import com.child.details.child_details.generated.resources.next_button_label
import com.child.details.child_details.generated.resources.save_button_label
import com.child.details.presentation.ChildDetailsPageViewModel
import com.child.details.presentation.navigation.ChildDetailsPageRoutes
import com.compose.shared.presentation.components.PrimaryButton
import com.compose.shared.presentation.components.PrimaryUserInputField
import com.compose.shared.extentions.childAgeInputKeyboardOptions
import com.compose.shared.util.KeyboardFocusRequestManager

@Composable
fun ChildAgeInputUi(
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
            childDetailsUiState.value.enableSecondContinueButton
        )
    }

    val keyboardActions = KeyboardActions(
        onNext = {
            primaryButtonClickEvent()
        }
    )

    /*
        Show back button container as user is navigating to this screen only if user is not editing
        information.
    */
    if (!childDetailsUiState.value.isEditingInformation) {
        childDetailsPageViewModel.showBackButtonContainer()
    }

    val primaryButtonLabel = if (childDetailsUiState.value.isEditingInformation) {
        Res.string.save_button_label
    } else {
        Res.string.next_button_label
    }

    Column(modifier = modifier) {
        // Child age input
        PrimaryUserInputField(
            value = childDetailsUiState.value.childAge,
            placeholder = Res.string.child_age_input_placeholder,
            hint = Res.string.child_age_input_hint,
            inputErrorMessage = Res.string.child_age_input_error,
            showErrorMessage = childDetailsUiState.value.showChildAgeInputError,
            keyboardOptions = KeyboardOptions.Default.childAgeInputKeyboardOptions(),
            keyboardActions = keyboardActions,
            focusRequester = KeyboardFocusRequestManager.getFocusRequester(),
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {
                childDetailsPageViewModel.updateChildAge(value = it)
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        // Button to jump to the child skill level input component
        PrimaryButton(
            label = primaryButtonLabel,
            enabled = childDetailsUiState.value.enableSecondContinueButton,
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
    enableSecondContinueButton: Boolean
) -> Unit =
    { childDetailsPageViewModel, childDetailsPageNavController, isEditingInformation, enableSecondContinueButton ->
        childDetailsPageViewModel.saveChildAge()
        when {
            enableSecondContinueButton && !isEditingInformation -> {
                childDetailsPageNavController.navigate(
                    route = ChildDetailsPageRoutes.CHILD_SWIMMING_SKILL_LEVEL_UI
                )
            }

            enableSecondContinueButton && isEditingInformation -> {
                childDetailsPageNavController.popBackStack()
            }
        }
    }