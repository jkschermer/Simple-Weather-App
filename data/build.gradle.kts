plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    kotlin("plugin.serialization") version "1.8.0"
}

android {
    flavorDimensions += "default"
    compileSdk = Versions.COMPILE_SDK

    defaultConfig {
        minSdk = Versions.MIN_SDK
        targetSdk = Versions.TARGET_SDK
    }

    buildTypes {
    }

    productFlavors {

    }

    compileOptions {
        sourceCompatibility = Versions.JVM
        targetCompatibility = Versions.JVM

        kotlinOptions {
            freeCompilerArgs = listOf(
                "-Xopt-in=kotlin.time.ExperimentalTime",
                "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            )
        }
    }

    kotlinOptions {
        jvmTarget = Versions.JVM.toString()
    }
    namespace = "simpleapp.data"
}

dependencies {

    // Modules
    implementation(project(":domain"))

    // Android
    implementation("androidx.core:core-ktx:${Versions.ANDROID_X_CORE_KTX}")
    implementation("androidx.security:security-crypto:${Versions.ANDROID_SECURITY_CRYPTO}")

    // Kotlin
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINES}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:${Versions.COROUTINES}")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:${Versions.KOTLIN_DATETIME}")

    // Storage
    api("androidx.security:security-crypto:${Versions.ANDROID_X_SECURITY_CRYPTO}")

    // Networking
    api("io.ktor:ktor-client-okhttp:${Versions.KTOR}")
    api("io.ktor:ktor-client-serialization:${Versions.KTOR}")
    api("org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.KOTLIN_SERIALIZATION}")
    api("io.ktor:ktor-client-logging-jvm:${Versions.KTOR}")

    // Utility
    implementation("org.apache.commons:commons-text:${Versions.APACHE_COMMONS_TEXT}")
}
