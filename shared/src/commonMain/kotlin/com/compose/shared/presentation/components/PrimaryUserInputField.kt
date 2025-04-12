package com.compose.shared.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.ColorProducer
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.compose.shared.shared.generated.resources.Res
import com.compose.shared.shared.generated.resources.dummy_input_error_label
import com.design.system.api.ComposeAppTheme
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

// Primary user input field to be used across all pages.
@Composable
fun PrimaryUserInputField(
    value: String,
    placeholder: StringResource,
    hint: StringResource,
    modifier: Modifier = Modifier,
    inputErrorMessage: StringResource = Res.string.dummy_input_error_label,
    showErrorMessage: Boolean = false,
    focusRequester: FocusRequester = remember { FocusRequester() },
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    keyboardActions: KeyboardActions = KeyboardActions(),
    onValueChange: (String) -> Unit
) {
    val backgroundContainerModifier = modifier.border(
        shape = RoundedCornerShape(1000.dp),
        color = ComposeAppTheme.colors.borderColor,
        width = 1.dp
    )

    val primaryFontColor = ComposeAppTheme.colors.primaryFontColor
    val inputHintFontColor = ComposeAppTheme.colors.inputHintColor
    val inputErrorFontColor = ComposeAppTheme.colors.inputErrorColor

    val basicTextFieldModifier = modifier
        .padding(horizontal = 28.dp, vertical = 20.dp)
        .focusRequester(focusRequester)

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
        Spacer(modifier = Modifier.height(12.dp))
        // Background container
        Box(contentAlignment = Alignment.Center, modifier = backgroundContainerModifier) {
            // Display input hint when the user hasn't entered any text.
            if (value.isEmpty()) {
                BasicText(
                    text = stringResource(resource = hint),
                    style = ComposeAppTheme.typography.inputLabel.copy(textAlign = TextAlign.Center),
                    color = ColorProducer { inputHintFontColor },
                    maxLines = 1,
                    modifier = modifier
                )
            }
            BasicTextField(
                value = TextFieldValue(text = value, selection = TextRange(index = value.length)),
                singleLine = true,
                keyboardOptions = keyboardOptions,
                textStyle = ComposeAppTheme.typography.inputLabel.copy(
                    color = ComposeAppTheme.colors.primaryFontColor,
                    textAlign = TextAlign.Center
                ),
                keyboardActions = keyboardActions,
                modifier = basicTextFieldModifier,
                onValueChange = { onValueChange(it.text) }
            ) { innerTextField ->
                innerTextField()
            }
        }
        // A container that holds the error message and shows only when there is an error.
        AnimatedVisibility(value.isNotEmpty() && showErrorMessage) {
            Column(modifier = modifier.padding(horizontal = 20.dp)) {
                Spacer(modifier = Modifier.height(20.dp))
                BasicText(
                    text = stringResource(resource = inputErrorMessage),
                    style = ComposeAppTheme.typography.inputError.copy(textAlign = TextAlign.Center),
                    color = { inputErrorFontColor },
                    modifier = modifier
                )
            }
        }
    }
}