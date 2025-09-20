buildscript {
    val kotlinVersion by extra("2.2.20")
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }
    dependencies {
        val navigationVersion = "2.9.4"
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$navigationVersion")
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.13.0" apply false
    id("com.android.library") version "8.13.0" apply false
    id("org.jetbrains.kotlin.android") version "2.2.20" apply false
    id("org.jetbrains.kotlin.jvm") version "2.2.20" apply false
    id("com.google.dagger.hilt.android") version "2.57.1" apply false
    id("com.google.devtools.ksp") version "2.0.21-1.0.27" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.2.20" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

