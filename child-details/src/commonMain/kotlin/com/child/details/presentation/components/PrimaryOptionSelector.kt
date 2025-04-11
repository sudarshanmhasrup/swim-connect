package com.child.details.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorProducer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.child.details.child_details.generated.resources.Res
import com.child.details.child_details.generated.resources.ic_selection
import com.child.details.model.SelectionOption
import com.design.system.api.ComposeAppTheme
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun PrimaryOptionSelection(
    placeholder: StringResource,
    optionList: List<SelectionOption>,
    currentSelectedOption: String? = null,
    onOptionSelectionChanged: (SelectionOption) -> Unit,
    modifier: Modifier = Modifier
) {
    val primaryFontColor = ComposeAppTheme.colors.primaryFontColor

    Column(modifier = modifier) {
        // A box that holds a placeholder for this input field.
        Box(modifier = modifier.padding(horizontal = 8.dp)) {
            BasicText(
                text = stringResource(resource = placeholder),
                style = ComposeAppTheme.typography.inputPlaceholder,
                maxLines = 1,
                color = ColorProducer { primaryFontColor }
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        optionList.forEach {
            OptionLabel(
                label = it.label,
                isSelected = it.option.toString() == currentSelectedOption,
                modifier = modifier,
                onOptionSelected = { onOptionSelectionChanged(it) }
            )
            if (it != optionList.last()) {
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@Composable
private fun OptionLabel(
    label: StringResource,
    isSelected: Boolean = false,
    onOptionSelected: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val labelColor = if (isSelected) {
        ComposeAppTheme.colors.primaryColor
    } else {
        ComposeAppTheme.colors.primaryFontColor
    }
    val primaryColor = ComposeAppTheme.colors.primaryColor
    val backgroundColor = if (isSelected) {
        ComposeAppTheme.colors.backgroundColor
    } else {
        ComposeAppTheme.colors.secondaryColor
    }

    val backgroundContainerModifier = modifier
        .clip(shape = RoundedCornerShape(1000.dp))
        .clickable(
            indication = null,
            interactionSource = null,
            onClick = onOptionSelected
        )
        .border(
            width = 1.dp,
            color = if (isSelected) primaryColor else Color.Transparent,
            shape = RoundedCornerShape(1000.dp)
        )
        .background(color = backgroundColor)
        .padding(horizontal = 24.dp, vertical = 20.dp)

    // Background container
    Box(modifier = backgroundContainerModifier) {
        BasicText(
            text = stringResource(resource = label),
            style = ComposeAppTheme.typography.inputLabel.copy(textAlign = TextAlign.Center),
            maxLines = 1,
            color = ColorProducer { labelColor },
            modifier = Modifier.fillMaxWidth()
        )
        Box(contentAlignment = Alignment.CenterEnd, modifier = Modifier.fillMaxWidth()) {
            // Show selection icon if the option is selected.
            if (isSelected) {
                Image(
                    painter = painterResource(resource = Res.drawable.ic_selection),
                    contentDescription = null,
                )
            }
        }
    }
}