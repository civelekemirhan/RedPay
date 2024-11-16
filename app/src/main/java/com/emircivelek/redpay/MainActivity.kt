package com.emircivelek.redpay

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.compose.rememberNavController
import com.emircivelek.redpay.navigation.SetUpNavGraph
import com.emircivelek.redpay.ui.theme.BackRed
import com.emircivelek.redpay.ui.theme.RedPayTheme
import com.google.firebase.Firebase
import com.google.firebase.appcheck.appCheck
import com.google.firebase.appcheck.playintegrity.PlayIntegrityAppCheckProviderFactory
import com.google.firebase.initialize
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Firebase.initialize(context = this)
        Firebase.appCheck.installAppCheckProviderFactory(
            PlayIntegrityAppCheckProviderFactory.getInstance(),
        )
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


