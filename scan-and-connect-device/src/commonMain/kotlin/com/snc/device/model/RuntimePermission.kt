package com.snc.device.model

import dev.icerock.moko.permissions.Permission
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

data class RuntimePermission(
    val image: DrawableResource,
    val label: StringResource,
    val description: StringResource,
    val isPermissionGranted: Boolean,
    val permission: Permission
)
