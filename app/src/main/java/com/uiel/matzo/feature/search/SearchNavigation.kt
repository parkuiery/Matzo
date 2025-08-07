package com.uiel.matzo.feature.search

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.uiel.matzo.model.MealType
import com.uiel.matzo.model.WorkingType
import kotlinx.serialization.Serializable

@Serializable
data class SearchRoute(
    val workingType: WorkingType,
    val mealType: MealType,
)

fun NavController.navigateToSearch(
    workingType: WorkingType,
    mealType: MealType,
    navOptions: NavOptions? = null,
) = navigate(route = SearchRoute(
    workingType = workingType,
    mealType = mealType,
), navOptions = navOptions)

fun NavGraphBuilder.searchScreen(
    onBackClick: () -> Unit,
    onNavigateToCheck: (String,Boolean) -> Unit,
) {
    composable<SearchRoute> {
        SearchScreen(
            onBackClick = onBackClick,
            onNavigateToCheck = onNavigateToCheck,
        )
    }
}