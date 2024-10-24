package com.emircivelek.redpay.feature.ui.auth

import com.google.firebase.auth.FirebaseUser

sealed class AuthState {

    object Idle : AuthState()
    object CodeSent : AuthState()
    data class Success(val user: FirebaseUser?) : AuthState()
    data class Error(val message: String?) : AuthState()


}