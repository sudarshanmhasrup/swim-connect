package com.snc.device.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.snc.device.presentation.navigation.DiscoverDevicesPageNavigation
import com.snc.device.util.DiscoverDevicesPageLifecycleManager
import dev.icerock.moko.permissions.compose.BindEffect
import dev.icerock.moko.permissions.compose.rememberPermissionsControllerFactory

@Composable
fun DiscoverDevicesPage(modifier: Modifier = Modifier) {
    val factory = rememberPermissionsControllerFactory()
    val permissionController = remember(factory) { factory.createPermissionsController() }

    BindEffect(permissionController)

    val discoverDevicesPageViewModel = viewModel {
        DiscoverDevicesPageViewModel(permissionController = permissionController)
    }

    DiscoverDevicesPageLifecycleManager.initialize(
        discoverDevicesPageViewModel = discoverDevicesPageViewModel
    )

    Column(modifier = Modifier.fillMaxSize()) {
        DiscoverDevicesPageNavigation(discoverDevicesPageViewModel = discoverDevicesPageViewModel)
    }
}