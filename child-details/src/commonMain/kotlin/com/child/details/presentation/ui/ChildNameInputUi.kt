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
import com.child.details.child_details.generated.resources.child_name_input_hint
import com.child.details.child_details.generated.resources.child_name_input_placeholder
import com.child.details.child_details.generated.resources.next_button_label
import com.child.details.child_details.generated.resources.save_button_label
import com.child.details.presentation.ChildDetailsPageViewModel
import com.child.details.presentation.navigation.ChildDetailsPageRoutes
import com.compose.shared.extentions.childNameInputKeyboardOptions
import com.compose.shared.presentation.components.PrimaryButton
import com.compose.shared.presentation.components.PrimaryUserInputField
import com.compose.shared.util.KeyboardFocusRequestManager

@Composable
fun ChildNameInputUi(
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
            childDetailsUiState.value.enableFirstContinueButton
        )
    }

    val keyboardActions = KeyboardActions(
        onNext = {
            primaryButtonClickEvent()
        }
    )

    // Hide back button container as user is navigating to this screen.
    childDetailsPageViewModel.hideBackButtonContainer()

    val primaryButtonLabel = if (childDetailsUiState.value.isEditingInformation) {
        Res.string.save_button_label
    } else {
        Res.string.next_button_label
    }

    Column(modifier = modifier) {
        // Child name input
        PrimaryUserInputField(
            value = childDetailsUiState.value.childName,
            placeholder = Res.string.child_name_input_placeholder,
            hint = Res.string.child_name_input_hint,
            keyboardOptions = KeyboardOptions.Default.childNameInputKeyboardOptions(),
            keyboardActions = keyboardActions,
            focusRequester = KeyboardFocusRequestManager.getFocusRequester(),
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {
                childDetailsPageViewModel.updateChildName(value = it)
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        // Button to jump to the child age input component
        PrimaryButton(
            label = primaryButtonLabel,
            enabled = childDetailsUiState.value.enableFirstContinueButton,
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
    enableFirstContinueButton: Boolean
) -> Unit =
    { childDetailsPageViewModel, childDetailsPageNavController, isEditingInformation, enableFirstContinueButton ->
        childDetailsPageViewModel.saveChildName()
        when {
            enableFirstContinueButton && !isEditingInformation -> {
                childDetailsPageNavController.navigate(
                    route = ChildDetailsPageRoutes.CHILD_AGE_INPUT_UI
                )
            }

            enableFirstContinueButton && isEditingInformation -> {
                childDetailsPageNavController.popBackStack()
            }
        }
    }