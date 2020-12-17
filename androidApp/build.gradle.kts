plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdkVersion(Versions.androidCompileSdk)
    defaultConfig {
        minSdkVersion(Versions.androidMinSdk)
        targetSdkVersion(Versions.androidTargetSdk)

        applicationId = "com.spreaker.kmm.androidApp"
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":shared"))

    // UI
    implementation(Deps.AndroidX.appcompat)
    implementation(Deps.AndroidX.activityKtx)
    implementation(Deps.AndroidX.constraintlayout)
    implementation(Deps.AndroidX.lifecycleExtensions)
    implementation(Deps.AndroidX.lifecycleViewmodelKtx)
    implementation(Deps.material)

    // Backend
    implementation(Deps.Coroutines.core)
    implementation(Deps.Ktor.android)

    // Testing
    testImplementation(Deps.junit)
    testImplementation(Deps.mockito)
    testImplementation("androidx.arch.core:core-testing:2.1.0")
}

