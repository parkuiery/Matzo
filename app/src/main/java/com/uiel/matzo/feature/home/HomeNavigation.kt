package com.uiel.matzo.feature.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.uiel.matzo.model.MealType
import com.uiel.matzo.model.WorkingType
import kotlinx.serialization.Serializable

@Serializable
data object HomeRoute

fun NavController.navigateToHome(
    navOptions: NavOptions? = null
) = navigate(route = HomeRoute, navOptions = navOptions)

fun NavGraphBuilder.homeScreen(
    onNavigateSearch: (WorkingType,MealType) -> Unit,
    onNavigateSetting: () -> Unit,
    onNavigateOutsider: (MealType) -> Unit,
) {
    composable<HomeRoute> {
        HomeScreen(
            onNavigateSearch = onNavigateSearch,
            onNavigateSetting = onNavigateSetting,
            onNavigateOutsider = onNavigateOutsider,
        )
    }
}