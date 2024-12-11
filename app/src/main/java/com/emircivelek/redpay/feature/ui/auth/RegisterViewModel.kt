package com.emircivelek.redpay.feature.ui.auth


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository
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

    private val _authState= MutableStateFlow<AuthState>(AuthState.Idle)
    val authState=_authState.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), AuthState.Idle)

    private val _verificationId = MutableStateFlow<String?>(null)





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

                    if(_registerState.value.name.isNotEmpty() && _registerState.value.surname.isNotEmpty() && _registerState.value.phoneNumber.isNotEmpty()){
                        if(_registerState.value.phoneNumber.length==10){
                            _authState.value=AuthState.Loading
                           authRepository.createUserWithPhoneNumber(
                                _registerState.value.phoneNumber,
                                event.activity,
                                _authState,
                                _verificationId,
                            )
                            Log.d("_authState.value",_authState.value.toString())
                        }else{
                            _authState.value=AuthState.Error("Telefon Numarası 10 haneli olmalıdır",false)
                        }

                        }else{

                        _authState.value=AuthState.Error("Lütfen tüm alanları doldurunuz",false)
                    }


                }

            }

            is RegisterEvent.CodeVerified -> {
                viewModelScope.launch {
                    val uuid = UUID.randomUUID()
                    val accountID = uuid.mostSignificantBits.toString().takeLast(10).toIntOrNull() ?: 0
                    val user=User(registerState.value.phoneNumber,registerState.value.name,registerState.value.surname,accountID.toLong())
                    authRepository.verifyCode(event.code,_authState,_verificationId,user)
                    Log.d("verificationId",_verificationId.value.toString())
                    _authState.value=AuthState.Loading
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