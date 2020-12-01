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

5 main layers: Presentation, UseCase, Domain, Data and Framework

## Presentation (or UI) layer

It’s the layer that interacts with the UI. Very platform dependent.

It manage the state of the UI.

Examples of classes:
- iOS: ViewController, View, any UIControl, ViewModel
- Android: Activity, Fragment, Presenter, ViewModel

Pattern: MVVM, MVP or MVC?

## UseCase layer

It contains mainly the actions that the user can trigger. It simplify access to the domain layer, forwarding actions down-layer and exposing data up-layer.

It's stateless.

## Domain layer

Also known as business logic. This layer has the rules of your business.

It might manager some business state.

Examples of classes:
- data models
- managers

## Data layer

In this layer, you have an abstract definition of the different data sources, and how they should be used.

Usually should contains repositories that hide the actual source of the data.

## Framework

It basically encapsulates the interaction with the underlying platform (iOS or Android) or services/libraries (networking library, database, Firebase, etc), so that the rest of the code can be agnostic and reusable.



