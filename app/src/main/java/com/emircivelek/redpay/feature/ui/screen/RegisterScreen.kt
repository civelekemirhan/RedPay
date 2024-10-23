package com.emircivelek.redpay.feature.ui.screen


import android.app.Activity
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.emircivelek.redpay.common.components.AuthButton
import com.emircivelek.redpay.common.components.AuthColumn
import com.emircivelek.redpay.common.components.CustomTextFieldWithBottomBorder
import com.emircivelek.redpay.feature.ui.auth.RegisterEvent
import com.emircivelek.redpay.feature.ui.auth.RegisterViewModel
import kotlinx.coroutines.launch


@Composable
fun RegisterScreen(navController: NavController) {

    val viewModel: RegisterViewModel = hiltViewModel<RegisterViewModel>()
    val state by viewModel.registerState.collectAsState()


    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    AuthColumn(true) {


        when (state.isCodeSent) {
            true -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Row {
                        CustomTextFieldWithBottomBorder(
                            value = state.verificationCode,
                            onValueChange = {viewModel::onEvent.invoke(RegisterEvent.SetVerificationCode(it))},
                            labelText ="Doğrulama Kodunu Giriniz",
                            width = 0.8f
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    AuthButton(
                        onClick = {
                            scope.launch {
                                viewModel::onEvent.invoke(RegisterEvent.CodeVerified(state.verificationCode))
                                Log.d("CodeSentBtn", "Sent")
                            }
                        },
                        text = "Onayla"
                    )
                }
            }

            false -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CustomTextFieldWithBottomBorder(
                        value = state.name,
                        onValueChange = { viewModel::onEvent.invoke(RegisterEvent.SetName(it)) },
                        labelText = "Adınızı Giriniz",
                        width = 0.8f
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    CustomTextFieldWithBottomBorder(
                        value = state.surname,
                        onValueChange = { viewModel::onEvent.invoke(RegisterEvent.SetSurname(it)) },
                        labelText = "Soyadınızı Giriniz",
                        width = 0.8f
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    CustomTextFieldWithBottomBorder(
                        value = state.phoneNumber,
                        onValueChange = { viewModel::onEvent.invoke(RegisterEvent.SetPhoneNumber(it)) },
                        labelText = "Telefon Numaranızı Giriniz",
                        width = 0.8f
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    AuthButton(
                        onClick = {
                            scope.launch {
                                viewModel::onEvent.invoke(RegisterEvent.CodeSent((context as Activity)))
                                Log.d("CodeSentBtn", "Sent")
                            }
                        },
                        text = "Kayıt Ol"
                    )


                }
            }
        }


    }

}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun RegisterScreenPreview() {
    RegisterScreen(navController = rememberNavController())
}