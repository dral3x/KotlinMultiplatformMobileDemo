# Experiment with Kotlin Multiplatform Mobile

# iOS setup

```
git clone git@github.com:touchlab/xcode-kotlin.git
xd xcode-kotlin
./setup-xcode11.sh
sudo ./colorsetup-xcode11.sh
```

## Install Java 11
```
brew tap AdoptOpenJDK/openjdk
brew install --cask adoptopenjdk1
```
to check, use `/usr/libexec/java_home -V`

# Android setup

Use Android Studio 2020.3.1 Canary 4

## Install Java 11
```
brew tap AdoptOpenJDK/openjdk
brew install --cask adoptopenjdk1
```
to check, use `/usr/libexec/java_home -V`

# Project architecture

- KMM project, with
    - iOS app
    - Android app
    - Shared library 

# Code architecture

Sort of *"Clean Architecture"*

3 main layers: Presentation, Domain, and Data

## Presentation (or UI) layer

It’s the layer that interacts with the UI. Very platform dependent.

It manage the state of the UI.

Examples of classes:
- iOS: ViewController, View, any UIControl, ViewModel
- Android: Activity, Fragment, Presenter, ViewModel

Pattern: MVVM, MVP or MVC?

## Domain layer

Also known as business logic. This layer has the rules of our business.

It might manage some state, even persist it.

Examples of classes:
- data models
- managers
- repository

## Data layer

In this layer, you have definitions of the different data sources.

It basically encapsulates the interaction with the underlying platform (iOS or Android) or services/libraries (networking library, database, Firebase, etc), so that the rest of the code can be agnostic and reusable.

Examples of classes:
- HTTP client
- database


# Useful Links

## Documentation and blogposts
- https://kotlinlang.org/docs/mobile/home.html
- https://dev.to/kuuurt/maximizing-code-sharing-between-android-and-ios-with-kotlin-multiplatform-54h8
- https://github.com/Kotlin/kotlinx.coroutines/blob/master/docs/shared-mutable-state-and-concurrency.md

## Dependencies
- https://github.com/touchlab/xcode-kotlin
- https://github.com/touchlab/CrashKiOS
- https://github.com/touchlab/Stately
- https://github.com/Kotlin/kotlinx.serialization
- https://github.com/badoo/Reaktive
- https://ktor.io/docs/quickstart-index.html

## Other projects
- https://github.com/touchlab/DroidconKotlin
- https://github.com/prof18/MoneyFlow
- https://github.com/Drjacky/TMDbMultiplatform
- https://github.com/kuuuurt/jokes-app-multiplatform


# Things to know

Calling Kotlin suspend functions from Swift/Objective-C is currently supported only on main thread