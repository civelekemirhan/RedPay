package com.emircivelek.redpay

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.compose.rememberNavController
import com.emircivelek.redpay.feature.ui.onboarding.OnBoardingScreen
import com.emircivelek.redpay.feature.ui.splash.SplashScreen
import com.emircivelek.redpay.navigation.SetUpNavGraph
import com.emircivelek.redpay.ui.theme.BackRed
import com.emircivelek.redpay.ui.theme.RedPayTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(BackRed.toArgb()),
            navigationBarStyle = SystemBarStyle.dark(BackRed.toArgb())
        )
        setContent {
            RedPayTheme {

                SetUpNavGraph(rememberNavController())

            }
        }
    }
}


