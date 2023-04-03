plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = Versions.COMPILE_SDK

    defaultConfig {
        minSdk = Versions.MIN_SDK
        targetSdk = Versions.TARGET_SDK
    }

    compileOptions {
        sourceCompatibility = Versions.JVM
        targetCompatibility = Versions.JVM
    }

    kotlinOptions {
        freeCompilerArgs = listOf("-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi")
        jvmTarget = Versions.JVM.toString()
    }
}

dependencies {
    // Modules
    implementation(project(":domain"))

    // Kotlin
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINES}")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:${Versions.KOTLIN_DATETIME}")

    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.ANDROID_LIFECYCLE}")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:${Versions.ANDROID_LIFECYCLE}")
    implementation("androidx.lifecycle:lifecycle-common-java8:${Versions.ANDROID_LIFECYCLE}")

    // Immutable
    api("org.jetbrains.kotlinx:kotlinx-collections-immutable:${Versions.KOTLIN_IMMUTABLE}")
}
