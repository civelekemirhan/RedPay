package com.emircivelek.redpay.feature.ui.auth

import android.app.Activity
import kotlinx.coroutines.flow.MutableStateFlow

interface AuthRepository {
    suspend fun createUserWithPhoneNumber(
        phoneNumber: String,
        activity: Activity,
        state: MutableStateFlow<AuthState>,
        verificationId: MutableStateFlow<String?>
    )

    suspend fun loginWithPhoneNumber()

    suspend fun verifyCode(
        code: String,
        state: MutableStateFlow<AuthState>,
        verificationId: MutableStateFlow<String?>,
        user:User
    )
}