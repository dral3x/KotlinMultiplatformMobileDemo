object Versions {
    // Android SDK
    const val androidCompileSdk = 30
    const val androidTargetSdk = 30
    const val androidMinSdk = 24
    const val androidBuildTools = "30.0.2"
    const val androidGradlePlugin = "7.0.0-alpha04"

    // iOS SDK
    const val iosDeploymentTarget = "13.0"

    // Tooling
    const val kotlin = "1.4.21"
    const val kotlinGradlePlugin = kotlin

    // Dependencies
    const val coroutines = "1.4.2"
    const val ktor = "1.4.2"
    const val serialization = "1.0.1"
    const val material = "1.2.1"

    object AndroidX {
        const val appcompat = "1.2.0"
        const val activity = "1.1.0"
        const val constraintlayout = "2.0.4"
        const val compose = "1.0.0-alpha09"
        const val core = "1.3.2"
        const val lifecycle = "2.2.0"
        const val test = "1.3.0"
        const val testing = "2.1.0"
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
        val testing = "androidx.arch.core:core-testing:${Versions.AndroidX.testing}"

        object Compose {
            val ui = "androidx.compose.ui:ui:${Versions.AndroidX.compose}"
            val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.AndroidX.compose}"
            val foundation = "androidx.compose.foundation:foundation:${Versions.AndroidX.compose}"
            val material = "androidx.compose.material:material:${Versions.AndroidX.compose}"
            val runtimeLivedata = "androidx.compose.runtime:runtime-livedata:${Versions.AndroidX.compose}"
            val uiTest = "androidx.compose.ui:ui-test-junit4:${Versions.AndroidX.compose}"
        }
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