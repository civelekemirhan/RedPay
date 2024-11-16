package com.emircivelek.redpay.feature.ui.auth

import com.google.firebase.auth.FirebaseUser

sealed class AuthState {

    object Idle : AuthState()
    object Loading:AuthState()
    data class CodeSent(val message: String?) : AuthState()
    data class Success(val message: String?) : AuthState()
    data class Error(val message: String?,val isCodeSent: Boolean) : AuthState()


}