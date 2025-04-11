package com.child.details.model

import org.jetbrains.compose.resources.StringResource

data class ConfirmChildDetailsListItem(
    val label: StringResource,
    val value: String,
    val route: String
)