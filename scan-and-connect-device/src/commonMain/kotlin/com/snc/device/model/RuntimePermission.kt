package com.snc.device.model

import dev.icerock.moko.permissions.Permission
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

data class RuntimePermission(
    val image: DrawableResource? = null,
    val label: StringResource? = null,
    val description: StringResource? = null,
    val isPermissionGranted: Boolean,
    val permission: Permission,
    val showOnUi: Boolean
)
