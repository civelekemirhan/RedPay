package com.emircivelek.redpay.feature.ui.auth



data class RegisterState (
    val phoneNumber: String = "",
    val name: String = "",
    val surname: String = "",
    val accountID: Long = 0,
    val verificationCode:String="",

)
