object Versions {
    // Android SDK
    const val androidCompileSdk = 30
    const val androidTargetSdk = 30
    const val androidMinSdk = 24

    // iOS SDK
    const val iosDeploymentTarget = "13.0"

    // Tooling
    const val kotlin = "1.4.10"
    const val buildTools = "30.0.2"
    const val androidGradlePlugin = "4.1.1"
    const val kotlinGradlePlugin = "1.4.20"

    // Dependencies
    const val coroutines = "1.4.2"
    const val ktor = "1.4.2"
    const val serialization = "1.0.1"
    const val material = "1.2.1"

    object AndroidX {
        val appcompat = "1.2.0"
        val activity = "1.1.0"
        val constraintlayout = "2.0.4"
        val core = "1.3.2"
        val lifecycle = "2.2.0"
        val test = "1.3.0"
    }

    // Test dependencies
    const val junit = "4.13.1"
    const val mockito = "3.3.3"
}

object Deps {

    object Coroutines {
        // https://github.com/Kotlin/kotlinx.coroutines
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}-native-mt" // multi-thread version
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    }

    object Serialization {
        // https://github.com/Kotlin/kotlinx.serialization
        const val json = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.serialization}"
    }

    object AndroidX {
        val appcompat = "androidx.appcompat:appcompat:${Versions.AndroidX.appcompat}"
        val activityKtx = "androidx.activity:activity-ktx:${Versions.AndroidX.activity}"
        val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.AndroidX.constraintlayout}"
        val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.AndroidX.lifecycle}"
        val lifecycleViewmodelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.AndroidX.lifecycle}"
    }

    object Ktor {
        const val core          = "io.ktor:ktor-client-core:${Versions.ktor}"
        const val serialization = "io.ktor:ktor-client-serialization:${Versions.ktor}"
        const val android       = "io.ktor:ktor-client-android:${Versions.ktor}"
        const val ios           = "io.ktor:ktor-client-ios:${Versions.ktor}"

        object Logging {
            const val common    = "io.ktor:ktor-client-logging:${Versions.ktor}"
            const val jvm       = "io.ktor:ktor-client-logging-jvm:${Versions.ktor}"
            const val native    = "io.ktor:ktor-client-logging-native:1.3.1" //TODO to upgrade
        }
    }

    const val material = "com.google.android.material:material:${Versions.material}"

    // For testing only
    const val junit = "junit:junit:${Versions.junit}"
    const val mockito = "org.mockito:mockito-core:${Versions.mockito}"
}