package com.compose.shared.extentions

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Modifier.backgroundContainerModifier(backgroundColor: Color): Modifier {
    return this
        .fillMaxSize()
        .background(color = backgroundColor)
}

@Composable
fun Modifier.pageModifier(scrollState: ScrollState): Modifier {
    return this
        .statusBarsPadding()
        .statusBarsPadding()
        .fillMaxSize()
        .verticalScroll(scrollState)
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
fun Modifier.childDetailsPageHeadingAndMessageModifier(): Modifier {
    return this
        .fillMaxWidth()
        .statusBarsPadding()
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

@Composable
fun Modifier.discoverDevicesPageHeadingAndMessageModifier(): Modifier {
    return this
        .statusBarsPadding()
        .fillMaxWidth()
        .padding(start = 20.dp, end = 20.dp, top = 40.dp)
}

@Composable
fun Modifier.scanningStatusAndScanButtonModifier(): Modifier {
    return this
        .fillMaxWidth()
        .padding(all = 20.dp)
}

@Composable
fun Modifier.foundDeviceListAdapterBoxModifier(): Modifier {
    return this
        .fillMaxWidth()
        .padding(horizontal = 20.dp)
}


@Composable
fun Modifier.foundDeviceListAdapterModifier(backgroundColor: Color): Modifier {
    return this
        .background(color = backgroundColor, shape = RoundedCornerShape(20.dp))
        .fillMaxWidth()
        .padding(all = 20.dp)
}

@Composable
fun Modifier.deviceIconAndContainerModifier(backgroundColor: Color): Modifier {
    return this
        .background(color = backgroundColor, shape = RoundedCornerShape(1000.dp))
        .padding(horizontal = 16.dp, vertical = 12.dp)
}

@Composable
fun Modifier.dialogBoxUiModifier(scrollState: ScrollState): Modifier {
    return this
        .fillMaxWidth()
        .verticalScroll(scrollState)
        .statusBarsPadding()
}

@Composable
fun Modifier.dialogBoxDoneButtonAndContainer(): Modifier {
    return this
        .fillMaxWidth()
        .padding(horizontal = 8.dp, vertical = 20.dp)
}

@Composable
fun Modifier.dialogueBoxImageAndContainerModifier(): Modifier {
    return this
        .fillMaxWidth()
        .padding(top = 20.dp, bottom = 40.dp)
}

@Composable
fun Modifier.dialogueBoxHeadingAndMessageModifier(): Modifier {
    return this
        .fillMaxWidth()
        .padding(horizontal = 20.dp)
}

@Composable
fun Modifier.permissionsListModifier(scrollState: ScrollState): Modifier {
    return this
        .fillMaxWidth()
        .padding(horizontal = 8.dp, vertical = 20.dp)
}

@Composable
fun Modifier.permissionListAdapterModifier(): Modifier {
    return this
        .fillMaxWidth()
        .padding(horizontal = 20.dp)
}

@Composable
fun Modifier.permissionImageModifier(): Modifier {
    return this
        .height(28.dp)
        .width(28.dp)
}