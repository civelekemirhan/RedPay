package com.emircivelek.redpay.common.di

import android.app.Activity
import com.emircivelek.redpay.common.FirebaseOperations
import com.emircivelek.redpay.feature.ui.auth.AuthRepository
import com.emircivelek.redpay.feature.ui.auth.AuthRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides

import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {


    @Provides
    @Singleton
    fun provideFirebaseOperations(): FirebaseOperations {
        return FirebaseOperations()
    }
    @Provides
    @Singleton
    fun provideAuthRepository(firebaseOperations: FirebaseOperations): AuthRepository {
        return AuthRepositoryImpl(firebaseOperations)
    }

}