# Experiment for PodStories app

# Project architecture

- Kotlin Multiplaform project, with
    - iOS app
    - Android app
    - Shared library 

# Code architecture

Sort of *"MVVM with Clean Architecture"*

3 main layers: Presentation, Domain, Data

## Presentation layer

It contains:
- UI code

Examples:
- iOS: ViewController, View, any UIControl, theming, ViewModel
- Android: Activity, Fragment, theming, ViewModel

## Domain layer

It contains:
- managers
- repositories
- data models (Show, Episode, User, etc)

## Data layer

It contains:
- http client
- database

# Services to integrate

- Logging (local + remote)
- Firebase (crash reporting, push notifications)
