# Experiment with Kotlin Multiplatform Mobile

# iOS setup

```
git clone git@github.com:touchlab/xcode-kotlin.git
xd xcode-kotlin
./setup-xcode11.sh
sudo ./colorsetup-xcode11.sh
```

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



