package com.uiel.matzo.feature.outsider

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.uiel.matzo.model.MealType
import kotlinx.serialization.Serializable

@Serializable
data class OutsiderRoute(
    val mealType: MealType,
)

fun NavController.navigateToOutsider(
    mealType: MealType,
    navOptions: NavOptions? = null
) = navigate(
    route = OutsiderRoute(
        mealType = mealType,
    ),
    navOptions = navOptions,
)

fun NavGraphBuilder.outsiderScreen(
    onBackClick: () -> Unit,
) {
    composable<OutsiderRoute> {
        OutsiderScreen(
            onBackClick = onBackClick,
        )
    }
}