pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        jcenter()
        mavenCentral()
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.namespace == "com.android") {
                useModule("com.android.tools.build:gradle:4.1.1") // Keep it in sync with Versions.androidGradlePlugin
            }
        }
    }
}
rootProject.name = "SpreakerKMMApp"

include(":androidApp")
include(":shared")

