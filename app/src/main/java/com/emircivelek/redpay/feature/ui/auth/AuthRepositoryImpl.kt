package com.emircivelek.redpay.feature.ui.auth

import android.app.Activity
import com.emircivelek.redpay.common.FirebaseOperations
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(val firebaseAuth: FirebaseOperations) :
    AuthRepository {

    override suspend fun createUserWithPhoneNumber(
        phoneNumber: String,
        activity: Activity,
        state: MutableStateFlow<AuthState>,
        verificationId: MutableStateFlow<String?>
    ) {
        firebaseAuth.startPhoneNumberVerification(phoneNumber, activity, state,verificationId)
    }

    override suspend fun loginWithPhoneNumber() {
        TODO("Not yet implemented")
    }

    override suspend fun verifyCode(
        code: String,
        state: MutableStateFlow<AuthState>,
        verificationId: MutableStateFlow<String?>
    ) {
        firebaseAuth.verifyCode(code,state,verificationId)
    }

}