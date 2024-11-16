package com.emircivelek.redpay.feature.ui.auth

import android.app.Activity

sealed interface LoginEvent {

    data class SetPhoneNumber(val phoneNumber: String):LoginEvent
    data class SetVerificationCode(val verificationCode:String):LoginEvent
    data class CodeSent(val activity: Activity):LoginEvent
    data class CodeVerified(val code: String) : LoginEvent

}