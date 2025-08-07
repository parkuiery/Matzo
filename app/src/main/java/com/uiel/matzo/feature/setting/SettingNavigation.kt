package com.uiel.matzo.feature.setting

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object SettingRoute

fun NavController.navigateToSetting(
    navOptions: NavOptions? = null
) = navigate(
    route = SettingRoute,
    navOptions = navOptions,
)

fun NavGraphBuilder.settingScreen(
    onBackClick: () -> Unit,
) {
    composable<SettingRoute> {
        SettingScreen(onBackClick = onBackClick)
    }
}