package com.emircivelek.redpay.navigation


import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation

fun NavGraphBuilder.ContentNavGraph(navController: NavHostController) {
    navigation(
        route = NavigationConstant.CONTENT_GRAPH_ROUTE,
        startDestination = NavigationConstant.HOME_SCREEN
    ) {


    }
}