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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import com.emircivelek.redpay.feature.ui.auth.AuthState
import com.emircivelek.redpay.feature.ui.auth.RegisterEvent
import com.emircivelek.redpay.feature.ui.auth.RegisterViewModel
import com.emircivelek.redpay.navigation.NavigationConstant
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue


@Composable
fun RegisterScreen(navController: NavController) {

    val viewModel: RegisterViewModel = hiltViewModel<RegisterViewModel>()
    val state by viewModel.registerState.collectAsState()
    val authState by viewModel.authState.collectAsState()


    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    AuthColumn(true) {


        when (authState) {
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
                                    RegisterEvent.SetVerificationCode(
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
                                viewModel::onEvent.invoke(RegisterEvent.CodeVerified(state.verificationCode))
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

            AuthState.Idle -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CustomTextFieldWithBottomBorder(
                        value = state.name,
                        onValueChange = { viewModel::onEvent.invoke(RegisterEvent.SetName(it)) },
                        labelText = "Adınızı Giriniz",
                        width = 0.9f
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    CustomTextFieldWithBottomBorder(
                        value = state.surname,
                        onValueChange = { viewModel::onEvent.invoke(RegisterEvent.SetSurname(it)) },
                        labelText = "Soyadınızı Giriniz",
                        width = 0.9f
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    CustomTextFieldWithBottomBorder(
                        value = state.phoneNumber,
                        onValueChange = { viewModel::onEvent.invoke(RegisterEvent.SetPhoneNumber(it)) },
                        labelText = "Telefon Numaranızı Giriniz",
                        width = 0.9f
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
            is AuthState.Error -> {
                if((authState as AuthState.Error).isCodeSent){
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
                                        RegisterEvent.SetVerificationCode(
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
                                    viewModel::onEvent.invoke(RegisterEvent.CodeVerified(state.verificationCode))
                                    Log.d("CodeSentBtn", "Sent")
                                }
                            },
                            text = "Onayla"
                        )
                    }
                }else{
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        CustomTextFieldWithBottomBorder(
                            value = state.name,
                            onValueChange = { viewModel::onEvent.invoke(RegisterEvent.SetName(it)) },
                            labelText = "Adınızı Giriniz",
                            width = 0.9f
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        CustomTextFieldWithBottomBorder(
                            value = state.surname,
                            onValueChange = { viewModel::onEvent.invoke(RegisterEvent.SetSurname(it)) },
                            labelText = "Soyadınızı Giriniz",
                            width = 0.9f
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        CustomTextFieldWithBottomBorder(
                            value = state.phoneNumber,
                            onValueChange = { viewModel::onEvent.invoke(RegisterEvent.SetPhoneNumber(it)) },
                            labelText = "Telefon Numaranızı Giriniz",
                            width = 0.9f
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
                LaunchedEffect(Unit) {
                    Toast.makeText(context,(authState as AuthState.Error).message,Toast.LENGTH_SHORT).show()
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
                                    RegisterEvent.SetVerificationCode(
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
                                viewModel::onEvent.invoke(RegisterEvent.CodeVerified(state.verificationCode))
                                Log.d("CodeSentBtn", "Sent")
                            }
                        },
                        text = "Onayla"
                    )
                }

                when (authState) {
                    is AuthState.Loading -> {
                        Toast.makeText(context, "Loading Just wait ...", Toast.LENGTH_SHORT).show()
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp), verticalAlignment = Alignment.Bottom) {
                            CircularProgressIndicator()
                        }
                    }

                    else -> {
                        LaunchedEffect(Unit){
                            val auth = Firebase.auth
                            Log.d("auth", auth.currentUser?.uid.toString())
                            Toast.makeText(context,"Kayıt Başarılı",Toast.LENGTH_SHORT).show()
                            navController.navigate(NavigationConstant.CONTENT_GRAPH_ROUTE){
                                popUpTo(NavigationConstant.AUTH_GRAPH_ROUTE){
                                    inclusive=true
                                }
                            }
                        }
                    }
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