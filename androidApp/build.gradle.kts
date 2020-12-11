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

    implementation(Deps.AndroidX.appcompat)
    implementation(Deps.AndroidX.activityKtx)
    implementation(Deps.AndroidX.constraintlayout)
    implementation(Deps.AndroidX.lifecycleExtensions)
    implementation(Deps.AndroidX.lifecycleViewmodelKtx)

    implementation(Deps.material)

    // Coroutine
    implementation(Deps.Coroutines.core)

    // HTTP
    implementation(Deps.Ktor.android)
}

