package com.emircivelek.redpay.feature.ui.auth

import android.app.Activity
import com.google.firebase.auth.FirebaseUser

sealed interface RegisterEvent {

    data class SetPhoneNumber(val phoneNumber: String) : RegisterEvent
    data class SetName(val name: String) : RegisterEvent
    data class SetSurname(val surname: String) : RegisterEvent
    data class CodeSent(val activity: Activity) :RegisterEvent
    data class SetVerificationCode(val verificationCode:String):RegisterEvent
    data class CodeVerified(val code: String) : RegisterEvent
}