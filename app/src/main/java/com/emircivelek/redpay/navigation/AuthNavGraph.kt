package com.emircivelek.redpay.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.emircivelek.redpay.feature.ui.Screen.LoginScreen
import com.emircivelek.redpay.feature.ui.Screen.OnBoardingScreen
import com.emircivelek.redpay.feature.ui.Screen.RegisterScreen
import com.emircivelek.redpay.feature.ui.Screen.SplashScreen

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
        composable(route = NavigationConstant.LOGIN_SCREEN) {
            LoginScreen(navController)
        }
        composable(route = NavigationConstant.REGISTER_SCREEN) {
            RegisterScreen(navController)
        }

    }

}