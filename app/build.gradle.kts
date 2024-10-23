plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.emircivelek.redpay"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.emircivelek.redpay"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.firebase.firestore)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation("com.google.android.gms:play-services-auth:21.2.0")

    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.0-alpha01")
    val nav_version = "2.8.0"

    implementation("androidx.navigation:navigation-compose:$nav_version")

    implementation("androidx.room:room-runtime:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")

    implementation("androidx.datastore:datastore-preferences:1.1.1")

    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.5")

    implementation("com.google.dagger:hilt-android:2.51.1")
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")

    // Hilt ile Jetpack Compose için ViewModel desteği
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
    // Hilt ile Jetpack Compose entegrasyonu
    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0")

    kapt("androidx.hilt:hilt-compiler:1.2.0")

    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    // retrofit

    implementation("com.squareup.retrofit2:retrofit:2.9.0")

// GSON

    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

// coroutine

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // OkHttp Logging Interceptor
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")

    implementation("io.coil-kt:coil-compose:2.2.2")

    // Import the BoM for the Firebase platform
    implementation(platform("com.google.firebase:firebase-bom:33.2.0"))


    // Add the dependency for the Firebase Authentication library
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation("com.google.firebase:firebase-auth")


    implementation(libs.androidx.navigation.compose)
}