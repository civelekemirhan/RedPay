package com.emircivelek.redpay.navigation


import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.emircivelek.redpay.feature.ui.screen.HomeScreen

fun NavGraphBuilder.ContentNavGraph(navController: NavHostController) {
    navigation(
        route = NavigationConstant.CONTENT_GRAPH_ROUTE,
        startDestination = NavigationConstant.HOME_SCREEN
    ) {
        composable(route = NavigationConstant.HOME_SCREEN){
            HomeScreen(navController = navController)
        }

    }
}