package com.emircivelek.redpay.feature.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    val authRepository: AuthRepository
) : ViewModel() {

    private val _loginState = MutableStateFlow(LoginState())
    val loginState = _loginState.map {
        it.copy(
            phoneNumber = it.phoneNumber
        )
    }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), LoginState())

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState =
        _authState.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), AuthState.Idle)

    private val _verificationId = MutableStateFlow<String?>(null)


    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.SetPhoneNumber -> {
                _loginState.update {
                    it.copy(
                        phoneNumber = event.phoneNumber
                    )
                }
            }

            is LoginEvent.CodeSent -> {
                if(loginState.value.phoneNumber != ""){
                    _authState.value = AuthState.CodeSent("Doğrulama kodu gönderildi")
                }else{
                    _authState.value = AuthState.Error("Lütfen telefon numaranızı giriniz",false)
                }
            }

            is LoginEvent.SetVerificationCode -> {
                _loginState.update {
                    it.copy(
                        verificationCode = event.verificationCode
                    )
                }
            }

            is LoginEvent.CodeVerified -> {
                viewModelScope.launch {

                    _authState.value=AuthState.Loading
                }
            }
        }

    }

}