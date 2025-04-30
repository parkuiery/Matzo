package com.uiel.matzo.feature.search

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object SearchRoute

fun NavController.navigateToSearch(
    navOptions: NavOptions? = null,
) = navigate(route = SearchRoute, navOptions = navOptions)

fun NavGraphBuilder.searchScreen(
    onBackClick: () -> Unit,
    onNavigateToCheck: () -> Unit,
) {
    composable<SearchRoute> {
        SearchScreen(
            onBackClick = onBackClick,
            onNavigateToCheck = onNavigateToCheck,
        )
    }
}