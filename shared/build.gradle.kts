plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization") version "1.4.0"
    id("com.android.library")
    id("org.jetbrains.kotlin.native.cocoapods")
}
group = "com.spreaker.experiment.kmm"
version = "1.0-SNAPSHOT"

repositories {
    gradlePluginPortal()
    google()
    jcenter()
    mavenCentral()
}

kotlin {
    android()
    ios()

    cocoapods {
        // Configure fields required by CocoaPods.
        summary = "Some description for a Kotlin/Native module"
        homepage = "Link to a Kotlin/Native module homepage"

        ios.deploymentTarget = "13.0"
    }

    sourceSets {

        val commonMain by getting {
            dependencies {
                implementation(Deps.Serialization.json)
                implementation(Deps.Coroutines.core)

                // Rx
                implementation(Deps.Reaktive.core)
                implementation(Deps.Reaktive.annotations)

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
    compileSdkVersion(29)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(24)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}
