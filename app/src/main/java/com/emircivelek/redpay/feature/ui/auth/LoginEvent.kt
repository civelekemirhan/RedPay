package com.emircivelek.redpay.feature.ui.auth

sealed interface LoginEvent {

    data class SetPhoneNumber(val phoneNumber: String):LoginEvent

}