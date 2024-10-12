package com.emircivelek.redpay.feature.ui.onboarding

import android.widget.Space
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OnBoardingItem(headerText: String, contentText: String) {

    Column(modifier=Modifier.fillMaxSize().padding(20.dp), verticalArrangement = Arrangement.Center){
        Text(text = headerText, fontSize = 30.sp, lineHeight = 30.sp, fontWeight = FontWeight.Bold, color = Color.White)
        Spacer(modifier =Modifier.height(20.dp))
        Text(
            text = contentText,
            fontSize = 20.sp,
            lineHeight = 20.sp,
            fontWeight = FontWeight.Normal,
            color = Color.White
        )
    }


}