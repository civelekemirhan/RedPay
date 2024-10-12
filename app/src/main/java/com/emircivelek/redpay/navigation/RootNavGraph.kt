package com.emircivelek.redpay.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun SetUpNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = NavigationConstant.AUTH_GRAPH_ROUTE,
        route = NavigationConstant.ROOT_GRAPH_ROUTE
    ) {

        AuthNavGraph(navController = navController)
        ContentNavGraph(navController = navController)

    }

}