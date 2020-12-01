object Versions {
    const val serialization = "1.0.1"
    const val coroutines = "1.4.2-native-mt"
    const val ktor = "1.4.1"
}

object Deps {

    object Coroutines {
        // https://github.com/Kotlin/kotlinx.coroutines
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    }

    object Serialization {
        // https://github.com/Kotlin/kotlinx.serialization
        const val json = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.serialization}"
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

}