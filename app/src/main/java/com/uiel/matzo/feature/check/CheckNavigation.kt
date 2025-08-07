package com.uiel.matzo.feature.check

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.uiel.matzo.model.CheckModel
import kotlinx.serialization.Serializable

@Serializable
data class CheckRoute(
    val name: String,
    val isSuccess: Boolean,
)

fun NavController.navigateToCheck(
    name: String,
    isSuccess: Boolean,
    navOptions: NavOptions? = null
) = navigate(
    route = CheckRoute(
       name = name,
        isSuccess = isSuccess,
    ), navOptions = navOptions
)

fun NavGraphBuilder.checkScreen(
    onNavigateHome: () -> Unit,
) {
    composable<CheckRoute> {
        val checkRoute: CheckRoute = it.toRoute()
        CheckScreen(
            name = checkRoute.name,
            isSuccess = checkRoute.isSuccess,
            onNavigateHome = onNavigateHome
        )
    }
}