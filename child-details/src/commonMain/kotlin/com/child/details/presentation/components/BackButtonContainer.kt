package com.child.details.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.child.details.child_details.generated.resources.Res
import com.child.details.child_details.generated.resources.ic_back
import org.jetbrains.compose.resources.painterResource

@Composable
fun BackButtonContainer(
    modifier: Modifier = Modifier,
    onBackButtonClicked: () -> Unit
) {
    val backIcon = Res.drawable.ic_back

    val backButtonModifier = Modifier
        .clip(shape = RoundedCornerShape(1000.dp))
        .clickable(interactionSource = null, indication = null, onClick = onBackButtonClicked)
        .height(48.dp)
        .width(48.dp)
    Box(modifier = modifier) {
        // Back button
        Box(contentAlignment = Alignment.Center, modifier = backButtonModifier) {
            Image(painter = painterResource(resource = backIcon), contentDescription = "Go back")
        }
    }
}