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
    implementation("androidx.core:core-ktx:1.17.0")
    implementation("androidx.appcompat:appcompat:1.7.1")
    implementation("com.google.android.material:material:1.13.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.3.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.7.0")
    //Navigation component
    implementation("androidx.navigation:navigation-fragment-ktx:2.9.4")
    implementation("androidx.navigation:navigation-ui-ktx:2.9.4")
    //Retrofit Dependecies
    implementation("com.squareup.retrofit2:retrofit:3.0.0")
    implementation("com.squareup.retrofit2:converter-gson:3.0.0")
    implementation("com.squareup.retrofit2:converter-moshi:3.0.0")
    implementation("com.squareup.okhttp3:logging-interceptor:5.1.0")
    //Dagger - Hilt
    implementation("com.google.dagger:hilt-android:2.57.1")
    kapt("com.google.dagger:hilt-android-compiler:2.57.1")
    kapt("androidx.hilt:hilt-compiler:1.3.0")
    implementation("androidx.hilt:hilt-work:1.3.0")
    //lifecycle
    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.9.4")
    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.9.4")
    //Glide
    implementation("com.github.bumptech.glide:glide:5.0.5")
    annotationProcessor("com.github.bumptech.glide:compiler:5.0.5")
    //Room Dependecies
    implementation("androidx.room:room-runtime:2.8.0")
    implementation("androidx.room:room-ktx:2.8.0")
    kapt("androidx.room:room-compiler:2.8.0")
    implementation("androidx.work:work-runtime:2.10.4")
    //SkeletonLayout
    implementation("com.faltenreich:skeletonlayout:6.0.0")
    implementation("androidx.paging:paging-runtime-ktx:3.3.6")

}