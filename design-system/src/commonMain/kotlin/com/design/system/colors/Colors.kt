package com.design.system.colors

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class Colors(
    val backgroundColor: Color = Color.Unspecified,
    val primaryColor: Color = Color.Unspecified,
    val secondaryColor: Color = Color.Unspecified,
    val primaryFontColor: Color = Color.Unspecified,
    val secondaryFontColor: Color = Color.Unspecified,
    val borderColor: Color = Color.Unspecified,
    val inputHintColor: Color = Color.Unspecified,
    val primaryDisabledColor: Color = Color.Unspecified,
    val inputErrorColor: Color = Color.Unspecified
)