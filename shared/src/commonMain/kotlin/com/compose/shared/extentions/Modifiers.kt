package com.compose.shared.extentions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Modifier.backgroundContainerModifier(color: Color): Modifier {
    return this
        .fillMaxSize()
        .background(color = color)
        .statusBarsPadding()
        .navigationBarsPadding()
}

@Composable
fun Modifier.landingPageHeadingAndMessageModifier(): Modifier {
    return this
        .fillMaxWidth()
        .padding(horizontal = 20.dp)
}

@Composable
fun Modifier.letsConnectButtonAndBackgroundModifier(): Modifier {
    return this
        .fillMaxWidth()
        .padding(horizontal = 20.dp)
}

@Composable
fun Modifier.finishAccountSetupPageHeadingAndMessageModifier(): Modifier {
    return this
        .fillMaxWidth()
        .padding(start = 24.dp, end = 24.dp, top = 40.dp)
}

@Composable
fun Modifier.finishSetupPagePrimaryButtonAndContainerModifier(): Modifier {
    return this
        .fillMaxWidth()
        .padding(all = 20.dp)
}