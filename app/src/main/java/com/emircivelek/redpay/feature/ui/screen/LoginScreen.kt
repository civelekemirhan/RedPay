package com.emircivelek.redpay.feature.ui.screen

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.emircivelek.redpay.common.components.AuthButton
import com.emircivelek.redpay.common.components.AuthColumn
import com.emircivelek.redpay.common.components.AuthSwitch
import com.emircivelek.redpay.common.components.CustomTextFieldWithBottomBorder
import com.emircivelek.redpay.feature.ui.auth.AuthState
import com.emircivelek.redpay.feature.ui.auth.LoginEvent
import com.emircivelek.redpay.feature.ui.auth.LoginViewModel
import com.emircivelek.redpay.navigation.NavigationConstant
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch


@Composable
fun LoginScreen(navController: NavController) {

    val viewModel: LoginViewModel = hiltViewModel()
    val state by viewModel.loginState.collectAsState()
    val authState = viewModel.authState.collectAsState()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    AuthColumn(true) {

        when (authState.value) {
            is AuthState.CodeSent -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Row {
                        CustomTextFieldWithBottomBorder(
                            value = state.verificationCode,
                            onValueChange = {
                                viewModel::onEvent.invoke(
                                    LoginEvent.SetVerificationCode(
                                        it
                                    )
                                )
                            },
                            labelText = "Doğrulama Kodunu Giriniz",
                            width = 0.9f
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    AuthButton(
                        onClick = {
                            scope.launch {
                                viewModel::onEvent.invoke(LoginEvent.CodeVerified(state.verificationCode))
                                Log.d("CodeSentBtn", "Sent")
                            }
                        },
                        text = "Onayla"
                    )
                }
                LaunchedEffect(Unit) {
                    Toast.makeText(
                        context,
                        (authState as AuthState.CodeSent).message,
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }

            is AuthState.Error -> {
                if ((authState as AuthState.Error).isCodeSent) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Row {
                            CustomTextFieldWithBottomBorder(
                                value = state.verificationCode,
                                onValueChange = {
                                    viewModel::onEvent.invoke(
                                        LoginEvent.SetVerificationCode(
                                            it
                                        )
                                    )
                                },
                                labelText = "Doğrulama Kodunu Giriniz",
                                width = 0.9f
                            )
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        AuthButton(
                            onClick = {
                                scope.launch {
                                    viewModel::onEvent.invoke(LoginEvent.CodeVerified(state.verificationCode))
                                    Log.d("CodeSentBtn", "Sent")
                                }
                            },
                            text = "Onayla"
                        )
                    }
                } else {
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
                                onValueChange = {
                                    viewModel::onEvent.invoke(
                                        LoginEvent.SetPhoneNumber(
                                            it
                                        )
                                    )
                                },
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
                            Column(horizontalAlignment = Alignment.Start) {
                                Text(text = "Beni Hatırla", color = Color.White)
                                AuthSwitch()
                            }
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                AuthButton(
                                    onClick = { viewModel::onEvent.invoke(LoginEvent.CodeSent(context as Activity)) },
                                    text = "Giriş Yap"
                                )
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
                LaunchedEffect(Unit) {
                    Toast.makeText(context,(authState as AuthState.Error).message,Toast.LENGTH_SHORT).show()
                }

            }

            AuthState.Idle -> {
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
                            onValueChange = { viewModel::onEvent.invoke(LoginEvent.SetPhoneNumber(it)) },
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
                        Column(horizontalAlignment = Alignment.Start) {
                            Text(text = "Beni Hatırla", color = Color.White)
                            AuthSwitch()
                        }
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            AuthButton(
                                onClick = { Log.d("LoginScreen", "LoginScreen Clicked") },
                                text = "Giriş Yap"
                            )
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

            else -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Row {
                        CustomTextFieldWithBottomBorder(
                            value = state.verificationCode,
                            onValueChange = {
                                viewModel::onEvent.invoke(
                                    LoginEvent.SetVerificationCode(
                                        it
                                    )
                                )
                            },
                            labelText = "Doğrulama Kodunu Giriniz",
                            width = 0.9f
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    AuthButton(
                        onClick = {
                            scope.launch {
                                viewModel::onEvent.invoke(LoginEvent.CodeVerified(state.verificationCode))
                                Log.d("CodeSentBtn", "Sent")
                            }
                        },
                        text = "Onayla"
                    )
                }
                when (authState.value) {
                    is AuthState.Loading -> {
                        Toast.makeText(context, "Loading Just wait ...", Toast.LENGTH_SHORT).show()
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp), verticalAlignment = Alignment.Bottom) {
                            CircularProgressIndicator()
                        }
                    }

                    else -> {
                        val auth = Firebase.auth
                        Log.d("auth", auth.currentUser?.uid.toString())
                        LaunchedEffect(Unit) {
                            Toast.makeText(context,"Kayıt Başarılı",Toast.LENGTH_SHORT).show()
                        }

                    }
                }
            }
        }


    }

}