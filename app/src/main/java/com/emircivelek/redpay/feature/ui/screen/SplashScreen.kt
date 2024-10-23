package com.emircivelek.redpay.feature.ui.screen

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextMotion
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.emircivelek.redpay.R
import com.emircivelek.redpay.common.components.AuthColumn
import com.emircivelek.redpay.navigation.NavigationConstant
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(navController: NavController) {
    val infiniteTransition = rememberInfiniteTransition(label = "infinite transition")
    val scale by infiniteTransition.animateFloat(
        initialValue = 0.1f,
        targetValue = 0.8f,
        animationSpec = infiniteRepeatable(tween(1800)),
        label = "scale"
    )
    val scope= rememberCoroutineScope()
   AuthColumn {
        Text(
            text = stringResource(id = R.string.app_name),
            lineHeight = 60.sp,
            fontSize = 60.sp,
            color = Color.White,
            letterSpacing = 5.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.graphicsLayer {
                scaleX = scale
                scaleY = scale
                transformOrigin = TransformOrigin.Center
            },
            style = LocalTextStyle.current.copy(textMotion = TextMotion.Animated)
        )

    }

    LaunchedEffect(key1 = true) {
        scope.launch {
            delay(1500)
            navController.navigate(NavigationConstant.ON_BOARDING_SCREEN)
        }
    }

}