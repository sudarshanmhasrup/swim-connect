package com.child.details.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun annotatedString(
    label: StringResource,
    value: String
): AnnotatedString {
    val extractedLabel = stringResource(resource = label)

    // Eg. "Name of the child: "
    val modifiedLabel = "$extractedLabel: "
    // Eg. "Name of the child: John"
    val rawString = modifiedLabel + value

    val endIndexOfFirstStyle = modifiedLabel.length - 1
    val startIndexOfSecondStyle = rawString.indexOf(value)
    val endIndexOfSecondStyle = rawString.length - 1

    print(endIndexOfSecondStyle)
    return buildAnnotatedString {
        append(rawString)
        addStyle(
            style = SpanStyle(fontWeight = FontWeight.SemiBold),
            start = 0,
            end = endIndexOfFirstStyle
        )
        addStyle(
            style = SpanStyle(fontWeight = FontWeight.Normal),
            start = startIndexOfSecondStyle,
            end = endIndexOfSecondStyle
        )
    }
}