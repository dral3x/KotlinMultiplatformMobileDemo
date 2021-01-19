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

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        //viewBinding = true // View Binding
        compose = true // Jetpack Compose
    }

    composeOptions {
        kotlinCompilerVersion = Versions.kotlin
        kotlinCompilerExtensionVersion = Versions.AndroidX.compose
    }

    packagingOptions {
        excludes += "META-INF/AL2.0"
        excludes += "META-INF/LGPL2.1"
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
        freeCompilerArgs = listOf(
            "-Xallow-jvm-ir-dependencies",
            "-Xskip-prerelease-check",
            "-Xuse-experimental=kotlinx.coroutines.ExperimentalCoroutinesApi"
        )
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

    // UI - Jetpack Compose
    implementation(Deps.AndroidX.Compose.ui)
    implementation(Deps.AndroidX.Compose.uiTooling)
    implementation(Deps.AndroidX.Compose.foundation)
    implementation(Deps.AndroidX.Compose.material)
    implementation(Deps.AndroidX.Compose.runtimeLivedata)

    // Backend
    implementation(Deps.Coroutines.core)
    implementation(Deps.Ktor.android)

    // Testing
    testImplementation(Deps.junit)
    testImplementation(Deps.mockito)
    testImplementation(Deps.AndroidX.testing)
    testImplementation(Deps.Coroutines.test)

    androidTestImplementation(Deps.AndroidX.Compose.uiTest)
}

