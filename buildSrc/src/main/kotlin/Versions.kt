import org.gradle.api.JavaVersion.VERSION_11

object Versions {

    // Build versions
    const val COMPILE_SDK = 33
    const val MIN_SDK = 24
    const val TARGET_SDK = 33

    // Compile versions
    const val ANDROID_GRADLE = "7.2.2"
    val JVM = VERSION_11

    // Kotlin
    const val KOTLIN = "1.8.0"
    const val COROUTINES = "1.5.1"
    const val KOTLIN_DATETIME = "0.3.1"
    const val KTOR = "1.6.7"
    const val KOTLIN_SERIALIZATION = "1.5.0-RC"
    const val KOTLIN_IMMUTABLE = "0.3.5"

    // Firebase
    const val FIREBASE_BOM = "28.2.1"
    const val FIREBASE_CRASHLYTICS_GRADLE = "2.8.0"
    const val FIREBASE_APP_DISTRIBUTION_GRADLE = "2.2.0"
    const val GOOGLE_GMS_SERVICE = "4.3.10"

    // Licenses
    const val LICENSES_CLASSPATH = "0.10.4"
    const val LICENSES = "17.0.0"

    // Android JetPack
    const val ANDROID_X_CORE_KTX = "1.6.0"
    const val ANDROID_LIFECYCLE = "2.3.1"
    const val ANDROID_NAVIGATION = "2.3.5"
    const val ANDROID_FRAGMENT_KTX = "1.3.6"
    const val ANDROID_ACTIVITY_KTX = "1.5.1"
    const val ANDROID_SECURITY_CRYPTO = "1.0.0"

    // Jetpack Compose
    const val KOTLIN_COMPILER_EXTENSION_VERSION = "1.4.0"
    const val COMPOSE_UI = "1.3.3"
    const val COMPOSE_VIEWMODEL = "2.6.0-beta01"
    const val COMPOSE_ACCOMPANIST = "0.28.0"
    const val DIRECTIONS = "1.7.27-beta"

    // COIL Imageloader
    const val COIL = "2.2.2"

    // Android - Design
    const val ANDROID_X_APPCOMPAT = "1.3.1"
    const val ANDROID_X_CONSTRAINT_LAYOUT = "2.1.1"
    const val ANDROID_X_SPLASH = "1.0.0-alpha01"
    const val GOOGLE_MATERIAL = "1.4.0"

    // LiveData
    const val LIVE_DATA_EXTENSION = "2.0.0"

    // Koin
    const val KOIN = "3.2.2"
    const val KOIN_COMPOSE = "3.3.0"

    // Beagle
    const val BEAGLE = "2.8.2"

    // Paris
    const val PARIS = "1.7.3"

    // Epoxy
    const val EPOXY = "4.6.4"

    // Lottie
    const val LOTTIE = "4.2.0"

    // Storage
    const val ANDROID_X_SECURITY_CRYPTO = "1.1.0-alpha03"

    // Utility
    const val APACHE_COMMONS_TEXT = "1.9"

    // Testing
    const val ANDROID_JUNIT5 = "1.8.0.0"
}
