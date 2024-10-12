package com.emircivelek.redpay.navigation

sealed class Screen(val route: String) {
    object OnBoardingScreen : Screen("onboarding_screen")
    object LoginScreen : Screen("login_screen")
    object SignUpScreen : Screen("sign_up_screen")
    object SplashScreen : Screen("splash_screen")
    object HomeScreen : Screen("home_screen")

}