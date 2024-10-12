package com.emircivelek.redpay.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.emircivelek.redpay.feature.ui.onboarding.OnBoardingScreen
import com.emircivelek.redpay.feature.ui.splash.SplashScreen

fun NavGraphBuilder.AuthNavGraph(navController: NavHostController) {

    navigation(
        route = NavigationConstant.AUTH_GRAPH_ROUTE,
        startDestination = NavigationConstant.SPLASH_SCREEN
    ) {
        composable(route = NavigationConstant.SPLASH_SCREEN) {
            SplashScreen(navController)
        }
        composable(route = NavigationConstant.ON_BOARDING_SCREEN) {
            OnBoardingScreen(navController)
        }

    }

}