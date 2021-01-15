pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        maven(url = "https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

rootProject.name = "SpreakerKMMApp"

enableFeaturePreview("GRADLE_METADATA")

include(":androidApp")
include(":shared")

