package com.uiel.matzo

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.uiel.matzo.feature.check.checkScreen
import com.uiel.matzo.feature.check.navigateToCheck
import com.uiel.matzo.feature.home.HomeRoute
import com.uiel.matzo.feature.home.homeScreen
import com.uiel.matzo.feature.home.navigateToHome
import com.uiel.matzo.feature.outsider.navigateToOutsider
import com.uiel.matzo.feature.outsider.outsiderScreen
import com.uiel.matzo.feature.search.navigateToSearch
import com.uiel.matzo.feature.search.searchScreen
import com.uiel.matzo.feature.setting.navigateToSetting
import com.uiel.matzo.feature.setting.settingScreen

@Composable
internal fun MatzoApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = HomeRoute,
    ) {
        homeScreen(
            onNavigateSearch = navController::navigateToSearch,
            onNavigateSetting = navController::navigateToSetting,
            onNavigateOutsider = navController::navigateToOutsider,
        )
        searchScreen(
            onBackClick = navController::navigateUp,
            onNavigateToCheck = navController::navigateToCheck
        )
        checkScreen(onNavigateHome = navController::navigateToHome)
        settingScreen(onBackClick = navController::navigateUp)
        outsiderScreen(onBackClick = navController::navigateUp)
    }
}