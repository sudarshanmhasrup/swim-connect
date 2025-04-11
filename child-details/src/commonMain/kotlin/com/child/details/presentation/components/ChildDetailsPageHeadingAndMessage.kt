package com.child.details.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.child.details.child_details.generated.resources.Res
import com.child.details.child_details.generated.resources.ic_child
import com.compose.shared.components.HeadingAndMessage
import com.compose.shared.extentions.childDetailsPageHeadingImageModifier
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun ChildDetailsPageHeadingAndMessage(
    heading: StringResource,
    message: StringResource,
    modifier: Modifier = Modifier
) {
    val childImage = Res.drawable.ic_child
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier) {
        // Child details page heading image
        Image(
            painter = painterResource(resource = childImage),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier.childDetailsPageHeadingImageModifier()
        )
        Spacer(modifier = Modifier.height(20.dp))
        HeadingAndMessage(heading = heading, message = message, modifier = Modifier.fillMaxWidth())
    }
}