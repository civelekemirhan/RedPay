package com.emircivelek.redpay.common


import android.app.Activity
import android.util.Log
import com.emircivelek.redpay.feature.ui.auth.AuthState
import com.google.firebase.Firebase
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.auth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class FirebaseOperations @Inject constructor() {

    private val auth = Firebase.auth

    // CoroutineScope için bir scope oluştur
    private val firebaseScope = CoroutineScope(Dispatchers.IO)

    suspend fun startPhoneNumberVerification(
        phoneNumber: String,
        activity: Activity,
        state: MutableStateFlow<AuthState>,
        verificationId: MutableStateFlow<String?>
    ) {
        val number="+90$phoneNumber"
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(number)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                    // Coroutine içinde sign-in işlemini başlat
                    firebaseScope.launch {
                        signInWithPhoneAuthCredential(credential, state)
                    }
                }

                override fun onVerificationFailed(e: FirebaseException) {
                    Log.d("Error",e.localizedMessage)
                    state.value = AuthState.Error(e.localizedMessage)
                }

                override fun onCodeSent(
                    verificationIdFromCallback: String,
                    token: PhoneAuthProvider.ForceResendingToken
                ) {

                    super.onCodeSent(verificationIdFromCallback, token)
                    verificationId.value = verificationIdFromCallback
                    state.value = AuthState.CodeSent
                    Log.d("verification",verificationIdFromCallback)

                }
            })
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    suspend fun verifyCode(
        code: String,
        state: MutableStateFlow<AuthState>,
        verificationId: MutableStateFlow<String?>
    ) {
        val credential = PhoneAuthProvider.getCredential(verificationId.value!!, code)
        firebaseScope.launch {
            signInWithPhoneAuthCredential(credential, state)
        }
    }

    private suspend fun signInWithPhoneAuthCredential(
        credential: PhoneAuthCredential,
        state: MutableStateFlow<AuthState>
    ) {
        Log.d("signInWithPhoneAuthCredential","signInWithPhoneAuthCredential Block is running")
        try {
            val result = auth.signInWithCredential(credential).await()
            state.value = AuthState.Success(result.user)
            Log.d("verification","Success and the code ${credential.smsCode.toString()}")
        } catch (e: Exception) {
            state.value = AuthState.Error(e.localizedMessage)
            Log.d("verification","Error and the code ${credential.smsCode.toString()}")
        }
    }
}