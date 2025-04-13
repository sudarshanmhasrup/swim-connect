package com.snc.device.data

import com.platform.api.TargetPlatform
import com.platform.api.getPlatform
import com.snc.device.model.RuntimePermission

object RuntimePermissionsList {
    private val currentPlatform = getPlatform()

    // Define permissions list according to the platform
    private val runtimePermissionsList = if (currentPlatform == TargetPlatform.ANDROID) {
        androidPermissionsList
    } else {
        androidPermissionsList
    }

    fun getRuntimePermissionsList(): List<RuntimePermission> {
        return runtimePermissionsList
    }
}