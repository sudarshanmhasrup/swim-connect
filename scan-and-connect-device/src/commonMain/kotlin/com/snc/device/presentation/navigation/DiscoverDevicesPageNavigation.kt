package com.snc.device.presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.snc.device.presentation.DiscoverDevicesPageViewModel
import com.snc.device.presentation.ui.DiscoverDevicesUi
import com.snc.device.presentation.ui.EnableBluetoothUi
import com.snc.device.presentation.ui.EnableLocationUi
import com.snc.device.presentation.ui.MissingPermissionsUi
import com.snc.device.presentation.ui.TryAgainUi

@Composable
fun DiscoverDevicesPageNavigation(discoverDevicesPageViewModel: DiscoverDevicesPageViewModel) {
    val discoverDevicesPageNavController = rememberNavController()
    val discoverDevicesPageUiState =
        discoverDevicesPageViewModel.discoverDevicesPageUiState.collectAsState()

    val startDestination = when {
        !discoverDevicesPageUiState.value.allPermissionsAreGranted -> {
            DiscoverDevicesPageRoutes.MISSING_PERMISSIONS_UI
        }

        !discoverDevicesPageUiState.value.isBluetoothEnabled -> {
            DiscoverDevicesPageRoutes.ENABLE_BLUETOOTH_UI
        }

        !discoverDevicesPageUiState.value.isLocationEnabled -> {
            DiscoverDevicesPageRoutes.ENABLE_LOCATION_UI
        }

        !discoverDevicesPageUiState.value.emptyDeviceList && !discoverDevicesPageUiState.value.isScanning -> {
            DiscoverDevicesPageRoutes.TRY_AGAIN_UI
        }

        else -> {
            DiscoverDevicesPageRoutes.DISCOVER_DEVICES_UI
        }
    }

    NavHost(
        navController = discoverDevicesPageNavController,
        startDestination = startDestination,
        enterTransition = {
            fadeIn(animationSpec = tween(0))
        },
        exitTransition = {
            fadeOut(animationSpec = tween(0))
        },
        popEnterTransition = {
            fadeIn(animationSpec = tween(0))
        },
        popExitTransition = {
            fadeOut(animationSpec = tween(0))
        }
    ) {
        composable(
            route = DiscoverDevicesPageRoutes.MISSING_PERMISSIONS_UI,
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Up,
                    animationSpec = tween(800)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Down,
                    animationSpec = tween(800)
                )
            }
        ) {
            MissingPermissionsUi(
                discoverDevicesPageViewModel = discoverDevicesPageViewModel,
                modifier = Modifier.fillMaxSize()
            )
        }
        composable(route = DiscoverDevicesPageRoutes.DISCOVER_DEVICES_UI) {
            DiscoverDevicesUi(
                discoverDevicesPageViewModel = discoverDevicesPageViewModel,
                modifier = Modifier.fillMaxSize()
            )
        }
        composable(route = DiscoverDevicesPageRoutes.ENABLE_BLUETOOTH_UI) {
            EnableBluetoothUi(
                discoverDevicesPageViewModel = discoverDevicesPageViewModel,
                modifier = Modifier.fillMaxSize()
            )
        }
        composable(route = DiscoverDevicesPageRoutes.ENABLE_LOCATION_UI) {
            EnableLocationUi(
                discoverDevicesPageViewModel = discoverDevicesPageViewModel,
                modifier = Modifier.fillMaxSize()
            )
        }
        composable(route = DiscoverDevicesPageRoutes.TRY_AGAIN_UI) {
            TryAgainUi(
                discoverDevicesPageViewModel = discoverDevicesPageViewModel,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}