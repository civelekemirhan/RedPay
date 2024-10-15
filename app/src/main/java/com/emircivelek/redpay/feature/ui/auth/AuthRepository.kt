package com.emircivelek.redpay.feature.ui.auth

interface AuthRepository {
   suspend fun createUserWithPhoneNumber()
   suspend fun loginWithPhoneNumber()
}