package com.emircivelek.redpay.feature.ui.auth


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    val authRepository: AuthRepository
) : ViewModel() {


    private val _registerState = MutableStateFlow(RegisterState())
    val registerState = _registerState.map {
        it.copy(
            phoneNumber = it.phoneNumber,
            name = it.name,
            surname = it.surname
        )
    }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), RegisterState())

    private val _verificationId = MutableStateFlow<String?>(null)

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState = _authState.asStateFlow()

    fun onEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.SetPhoneNumber -> {
                _registerState.update {
                    it.copy(
                        phoneNumber = event.phoneNumber
                    )
                }
            }

            is RegisterEvent.SetName -> {
                _registerState.update {
                    it.copy(
                        name = event.name
                    )
                }
            }

            is RegisterEvent.SetSurname -> {
                _registerState.update {
                    it.copy(
                        surname = event.surname
                    )
                }

            }

            is RegisterEvent.CodeSent -> {
                viewModelScope.launch {

                    _registerState.update {
                        it.copy(
                            isCodeSent = true
                        )
                    }

                    authRepository.createUserWithPhoneNumber(
                        _registerState.value.phoneNumber,
                        event.activity,
                        _authState,
                        _verificationId
                    )

                }
            }

            is RegisterEvent.CodeVerified -> {
                viewModelScope.launch {
                    authRepository.verifyCode(event.code,_authState,_verificationId)
                }


            }

            is RegisterEvent.SetVerificationCode -> {
                _registerState.update {
                    it.copy(
                        verificationCode = event.verificationCode
                    )
                }
            }
        }

    }

}