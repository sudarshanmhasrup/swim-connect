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
import com.child.details.child_details.generated.resources.child_swimming_skill_level_input_placeholder
import com.child.details.child_details.generated.resources.next_button_label
import com.child.details.child_details.generated.resources.save_button_label
import com.child.details.presentation.ChildDetailsPageViewModel
import com.child.details.presentation.components.PrimaryOptionSelection
import com.child.details.presentation.navigation.ChildDetailsPageRoutes
import com.compose.shared.components.PrimaryButton

@Composable
fun ChildSwimmingSkillLevelInputUi(
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

    Column(modifier = modifier) {
        // Child swimming skill level option input
        PrimaryOptionSelection(
            placeholder = Res.string.child_swimming_skill_level_input_placeholder,
            optionList = childDetailsPageViewModel.getChildSwimmingSkillLevelOptions(),
            currentSelectedOption = childDetailsUiState.value.childSwimmingSkillLevel.toString(),
            onOptionSelectionChanged = {
                childDetailsPageViewModel.updateChildSwimmingSkillLevelOption(value = it)
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(20.dp))
        // Button to jump to the alert time input component
        PrimaryButton(
            label = primaryButtonLabel,
            enabled = childDetailsUiState.value.enableThirdContinueButton,
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                primaryButtonClickEvent(
                    childDetailsPageViewModel,
                    childDetailsPageNavController,
                    childDetailsUiState.value.isEditingInformation,
                    childDetailsUiState.value.enableThirdContinueButton
                )
            }
        )
    }
}

private val primaryButtonClickEvent: (
    childDetailsPageViewModel: ChildDetailsPageViewModel,
    childDetailsPageNavController: NavController,
    isEditingInformation: Boolean,
    enableThirdContinueButton: Boolean
) -> Unit =
    { childDetailsPageViewModel, childDetailsPageNavController, isEditingInformation, enableThirdContinueButton ->
        childDetailsPageViewModel.saveChildSwimmingSkillLevel()
        when {
            enableThirdContinueButton && !isEditingInformation -> {
                childDetailsPageNavController.navigate(
                    route = ChildDetailsPageRoutes.ALERT_TIME_INPUT_UI
                )
            }

            enableThirdContinueButton && isEditingInformation -> {
                childDetailsPageNavController.popBackStack()
            }
        }
    }