package com.compose.shared.extentions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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

@Composable
fun Modifier.childDetailsPageHeadingModifier(): Modifier {
    return this
        .fillMaxWidth()
        .padding(start = 20.dp, end = 20.dp, top = 40.dp)
}

@Composable
fun Modifier.childDetailsPageHeadingImageModifier(): Modifier {
    return this
        .height(60.dp)
        .height(60.dp)
}

@Composable
fun Modifier.childDetailsPageInputUiModifier(): Modifier {
    return this
        .fillMaxSize()
        .padding(horizontal = 24.dp, vertical = 20.dp)
}

@Composable
fun Modifier.backButtonContainerModifier(): Modifier {
    return this
        .fillMaxSize()
        .padding(horizontal = 8.dp, vertical = 20.dp)
}

@Composable
fun Modifier.buttonStyleListItemModifier(): Modifier {
    return this
        .fillMaxWidth()
        .padding(vertical = 20.dp)
}

@Composable
fun Modifier.confirmChildDetailsUiModifier(): Modifier {
    return this
        .fillMaxSize()
        .padding(vertical = 20.dp)
}

@Composable
fun Modifier.saveAndContinueButtonBoxModifier(): Modifier {
    return this
        .fillMaxWidth()
        .padding(horizontal = 24.dp, vertical = 20.dp)
}