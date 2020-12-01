buildscript {
    repositories {
        gradlePluginPortal()
        jcenter()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.20")
        classpath("com.android.tools.build:gradle:4.1.1")
    }
}

group = "com.spreaker.kmm"
version = "1.0-SNAPSHOT"

allprojects {
    repositories {
        mavenCentral()
        maven("https://dl.bintray.com/badoo/maven")
    }
}