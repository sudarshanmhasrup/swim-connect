package com.snc.device.domain

import com.snc.device.data.RuntimePermissionsList
import com.snc.device.model.RuntimePermission

object RuntimePermissionsUseCase {
    fun getRuntimePermissionsList(): List<RuntimePermission> {
        return RuntimePermissionsList.getRuntimePermissionsList()
    }
}