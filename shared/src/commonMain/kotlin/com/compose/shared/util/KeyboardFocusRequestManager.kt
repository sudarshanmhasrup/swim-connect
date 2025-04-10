package com.compose.shared.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.focus.FocusRequester

object KeyboardFocusRequestManager {
    @Composable
    fun getFocusRequester(): FocusRequester {
        val focusRequester = remember { FocusRequester() }

        // Show keyboard manually and request focus to current text field
        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }
        return focusRequester
    }
}