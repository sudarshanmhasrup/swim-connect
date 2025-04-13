package com.design.system.typography

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle

@Immutable
data class Typography(
    val bodyHeadline: TextStyle = TextStyle(),
    val bodyHeadlineMedium: TextStyle = TextStyle(),
    val bodyLabelLarge: TextStyle = TextStyle(),
    val bodyHeadlineSmall: TextStyle = TextStyle(),
    val bodyMessage: TextStyle = TextStyle(),
    val buttonLabel: TextStyle = TextStyle(),
    val inputLabel: TextStyle = TextStyle(),
    val inputPlaceholder: TextStyle = TextStyle(),
    val inputError: TextStyle = TextStyle(),
    val bodyLabel: TextStyle = TextStyle(),
    val buttonLabelSmall: TextStyle = TextStyle()
)