import com.example.androidbase.presentation.ConfigData.COMPILE_SDK_VERSION
import com.example.androidbase.presentation.ConfigData.MIN_SDK_VERSION
import com.example.androidbase.presentation.ConfigData.TARGET_SDK_VERSION

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs.kotlin")
    id("dagger.hilt.android.plugin")
    id("com.google.devtools.ksp")
}

android {
    compileSdk = COMPILE_SDK_VERSION

    defaultConfig {
        applicationId = "com.example.themoviedb"
        minSdk = MIN_SDK_VERSION
        targetSdk = TARGET_SDK_VERSION
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    namespace = "com.example.themoviedb"
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit4)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.espresso)
    //Navigation component
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    //Retrofit Dependecies
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.retrofit.moshi)
    implementation(libs.okhttp.logging)
    //Dagger - Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    kapt(libs.androidx.hilt.compiler)
    implementation(libs.hilt.work)
    // ViewModel
    implementation(libs.lifecycle.viewmodel)
    //Glide
    implementation(libs.glide)
    annotationProcessor(libs.glide.compiler)

    implementation(libs.work.runtime)
    //SkeletonLayout
    implementation(libs.skeletonlayout)
    implementation(libs.paging)

}