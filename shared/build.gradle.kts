plugins {
    kotlin("multiplatform")
    id("kotlinx-serialization")
    id("com.android.library")
    id("org.jetbrains.kotlin.native.cocoapods")
}

// CocoaPods requires the podspec to have a version.
version = "1.0-SNAPSHOT"

android {
    compileSdkVersion(Versions.androidCompileSdk)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(Versions.androidMinSdk)
        targetSdkVersion(Versions.androidTargetSdk)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

// workaround for https://youtrack.jetbrains.com/issue/KT-43944
android {
    configurations {
        create("androidTestApi")
        create("androidTestDebugApi")
        create("androidTestReleaseApi")
        create("testApi")
        create("testDebugApi")
        create("testReleaseApi")
    }
}

kotlin {
    android()

    val sdkName: String? = System.getenv("SDK_NAME")
    val isiOSDevice = sdkName.orEmpty().startsWith("iphoneos")
    if (isiOSDevice) {
        iosArm64("iOS")
    } else {
        iosX64("iOS")
    }

    cocoapods {
        // Configure fields required by CocoaPods.
        summary = "Some description for a Kotlin/Native module"
        homepage = "Link to a Kotlin/Native module homepage"

        ios.deploymentTarget = Versions.iosDeploymentTarget
    }

    sourceSets {

        sourceSets["commonMain"].dependencies {
            implementation(Deps.Serialization.json)
            implementation(Deps.Coroutines.core)

            // HTTP
            implementation(Deps.Ktor.core)
            implementation(Deps.Ktor.serialization)
            implementation(Deps.Ktor.Logging.common)
        }
        sourceSets["commonTest"].dependencies {
            implementation(kotlin("test-common"))
            implementation(kotlin("test-annotations-common"))
        }

        // Android specific
        sourceSets["androidMain"].dependencies {
            // HTTP
            implementation(Deps.Ktor.android)
            implementation(Deps.Ktor.Logging.jvm)
        }
        sourceSets["androidTest"].dependencies {
            implementation(kotlin("test-junit"))
            implementation(Deps.junit)
        }

        // iOS specific
        sourceSets["iOSMain"].dependencies {
                // HTTP
                implementation(Deps.Ktor.ios)
                implementation(Deps.Ktor.Logging.native)

                // Crash reporting
                api("co.touchlab:crashkios:0.3.0")
        }
        sourceSets["iOSTest"].dependencies {
        }
    }
}