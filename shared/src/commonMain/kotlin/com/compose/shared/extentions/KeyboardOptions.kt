package com.compose.shared.extentions

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType

fun KeyboardOptions.childNameInputKeyboardOptions(): KeyboardOptions {
    return KeyboardOptions.Default.copy(
        capitalization = KeyboardCapitalization.Words,
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Next,
        autoCorrectEnabled = true
    )
}

fun KeyboardOptions.childAgeInputKeyboardOptions(): KeyboardOptions {
    return KeyboardOptions.Default.copy(
        keyboardType = KeyboardType.Number,
        imeAction = ImeAction.Next
    )
}