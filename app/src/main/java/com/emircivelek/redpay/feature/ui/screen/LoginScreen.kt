package com.emircivelek.redpay.feature.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.emircivelek.redpay.common.components.AuthButton
import com.emircivelek.redpay.common.components.AuthColumn
import com.emircivelek.redpay.common.components.AuthSwitch
import com.emircivelek.redpay.common.components.CustomTextFieldWithBottomBorder
import com.emircivelek.redpay.feature.ui.auth.LoginEvent
import com.emircivelek.redpay.feature.ui.auth.LoginViewModel
import com.emircivelek.redpay.navigation.NavigationConstant


@Composable
 fun LoginScreen(navController: NavController){

    val viewModel: LoginViewModel = hiltViewModel()
    val state by viewModel.loginState.collectAsState()

    AuthColumn(true) {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CustomTextFieldWithBottomBorder(
                    value = state.phoneNumber,
                    onValueChange = {viewModel::onEvent.invoke(LoginEvent.SetPhoneNumber(it))},
                    labelText = "Telefon Numaranızı Giriniz",
                    width = 0.9f
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.8f),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column( horizontalAlignment = Alignment.Start) {
                    Text(text = "Beni Hatırla", color = Color.White)
                    AuthSwitch()
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    AuthButton(onClick = { /*TODO*/ }, text = "Giriş Yap")
                }

            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(onClick = { navController.navigate(NavigationConstant.REGISTER_SCREEN) }) {
                    Text(
                        text = "Hesabın Yok mu? Kayıt OL",
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp
                    )
                }
            }


        }

    }

}