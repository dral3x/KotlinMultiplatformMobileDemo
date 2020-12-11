plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization") version "1.4.0"
    id("com.android.library")
    id("org.jetbrains.kotlin.native.cocoapods")
}
version = "1.0-SNAPSHOT"

kotlin {
    android()
    ios()

    cocoapods {
        // Configure fields required by CocoaPods.
        summary = "Some description for a Kotlin/Native module"
        homepage = "Link to a Kotlin/Native module homepage"

        ios.deploymentTarget = Versions.iosDeploymentTarget
    }

    sourceSets {

        val commonMain by getting {
            dependencies {
                implementation(Deps.Serialization.json)
                implementation(Deps.Coroutines.core)

                // HTTP
                implementation(Deps.Ktor.core)
                implementation(Deps.Ktor.serialization)
                implementation(Deps.Ktor.Logging.common)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        // Android specific
        val androidMain by getting {
            dependencies {

                // HTTP
                implementation(Deps.Ktor.android)
                implementation(Deps.Ktor.Logging.jvm)
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.12")
            }
        }

        // iOS specific
        val iosMain by getting {
            dependencies {

                // HTTP
                implementation(Deps.Ktor.ios)
                implementation(Deps.Ktor.Logging.native)

                // Crash reporting
                api("co.touchlab:crashkios:0.3.0")
            }
        }
        val iosTest by getting
    }
}

android {
    compileSdkVersion(Versions.androidCompileSdk)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(Versions.androidMinSdk)
        targetSdkVersion(Versions.androidTargetSdk)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}
