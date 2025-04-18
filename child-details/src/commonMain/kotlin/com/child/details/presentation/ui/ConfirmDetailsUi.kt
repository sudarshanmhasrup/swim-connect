package com.child.details.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorProducer
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.child.details.child_details.generated.resources.Res
import com.child.details.child_details.generated.resources.save_and_continue_button_label
import com.child.details.presentation.ChildDetailsPageViewModel
import com.child.details.util.annotatedString
import com.compose.shared.extentions.buttonStyleListItemModifier
import com.compose.shared.extentions.saveAndContinueButtonBoxModifier
import com.compose.shared.presentation.ComposeAppViewModel
import com.compose.shared.presentation.components.PrimaryButton
import com.compose.shared.presentation.routes.ComposeAppRoutes
import com.design.system.api.ComposeAppTheme

@Composable
fun ConfirmDetailsUi(
    composeAppViewModel: ComposeAppViewModel,
    childDetailsPageViewModel: ChildDetailsPageViewModel,
    composeAppNavController: NavController,
    childDetailsPageNavController: NavController,
    modifier: Modifier = Modifier
) {
    val confirmChildDetailsOptionList = childDetailsPageViewModel.getConfirmChildDetailsOptionList()

    // Show confirmation page heading
    childDetailsPageViewModel.showConfirmationPageHeading()

    Column(modifier = modifier) {
        confirmChildDetailsOptionList.forEach { option ->
            val annotatedString = annotatedString(label = option.label, value = option.value)
            ButtonStyleListItem(
                annotatedLabel = annotatedString,
                modifier = Modifier.buttonStyleListItemModifier(),
                onClick = {
                    childDetailsPageNavController.navigate(option.route)
                    childDetailsPageViewModel.turnOnEditingMode()
                    childDetailsPageViewModel.hideBackButtonContainer()
                    childDetailsPageViewModel.hideConfirmationPageHeading()
                }
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier.saveAndContinueButtonBoxModifier()) {
            PrimaryButton(
                label = Res.string.save_and_continue_button_label,
                enabled = true,
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    composeAppViewModel.saveChildDetailsPageAsViewed()
                    composeAppNavController.navigate(ComposeAppRoutes.DISCOVER_DEVICES_PAGE) {
                        popUpTo(ComposeAppRoutes.DISCOVER_DEVICES_PAGE) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}

@Composable
private fun ButtonStyleListItem(
    annotatedLabel: AnnotatedString,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val primaryColor = ComposeAppTheme.colors.primaryColor
    val primaryFontColor = ComposeAppTheme.colors.primaryFontColor

    val dividerModifier = Modifier
        .background(color = ComposeAppTheme.colors.borderColor)
        .fillMaxWidth()
        .height(1.dp)
    val backgroundContainerModifier = Modifier
        .clickable(onClick = onClick)
        .padding(horizontal = 24.dp)

    Column(modifier = backgroundContainerModifier) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
        ) {
            BasicText(
                text = annotatedLabel,
                style = ComposeAppTheme.typography.inputLabel,
                color = ColorProducer { primaryFontColor },
                maxLines = 2
            )
            BasicText(
                text = "Edit",
                style = ComposeAppTheme.typography.buttonLabelSmall,
                color = ColorProducer { primaryColor },
                maxLines = 1
            )
        }
        // Divider
        Spacer(modifier = dividerModifier)
    }
}