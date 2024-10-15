package com.emircivelek.redpay.feature.ui.auth

import com.emircivelek.redpay.common.FirebaseOperations
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(val firebaseAuth: FirebaseOperations):AuthRepository {
    override suspend fun createUserWithPhoneNumber() {
        TODO("Not yet implemented")
    }

    override suspend fun loginWithPhoneNumber() {
        TODO("Not yet implemented")
    }

}