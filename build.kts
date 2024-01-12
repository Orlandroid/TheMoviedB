buildscript {
    ext {
        kotlin_version = '1.9.21'
    }
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
    dependencies {
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
        def navigation_version = "2.6.0"
        def dagger_hilt_version = '2.48'
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$navigation_version")
        classpath "com.google.dagger:hilt-android-gradle-plugin:$dagger_hilt_version"
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id 'com.android.application' version '8.1.3' apply false
    id 'com.android.library' version '8.1.3' apply false
    id 'org.jetbrains.kotlin.android' version '1.9.21' apply false
    id 'com.google.devtools.ksp' version "1.8.10-1.0.9" apply false

}


tasks.register('clean', Delete) {
    delete rootProject.buildDir
}
