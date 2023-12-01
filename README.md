# MovieApp

MovieApp is an Android application that displays popular movies by utilizing The Movie DB API. It also incorporates functionality to store data locally using Room Db, allowing seamless data retrieval in case of network errors. The app is constructed using Jetpack Compose and adheres to the MVVM (Model-View-ViewModel) architecture pattern.
## Features

- **Display Popular Movies**: View a list of popular movies fetched from The Movie DB API.
- **Display Watched and Watch Later Lists**: View a list of movies that have been added to the Watched or Watch Later lists.
- **Details Screen**: Tap on a movie to see its details. Movie can be added to Watched or Watch Later lists.
- **MVVM Architecture**: Utilizes a clean architecture approach for better code organization and maintainability.
- **Jetpack Compose UI**: Implements modern UI components and layouts using Jetpack Compose for a dynamic and responsive user interface.
- **Room Database**: Stores movie data locally using Room Db for seamless data retrieval in case of network errors.
- **Dark Mode**: Supports both light and dark mode.
- **Screen Orientation**: Supports both portrait and landscape mode.
- **Animations**: Implements animations to create a shimmer effect on Popular Movie Screen.
- **Prefs**: Stores `Last updated date` and the `source` where the data was loaded from using Jetpack DataStore.
- **Custom Splash Screen**: Displays a custom splash screen while the app is loading.
- **Pull to Refresh**: Pull to refresh functionality to update the Popular Movies list.

<p align="center">
<img src="/preview/1.png" />
</p>

<p align="center">
<img src="/preview/2.png" />
</p>

<p float="left">
<img src="/preview/pull_to_refresh.gif" width="32%" />
<img src="/preview/shimmer_animation.gif" width="32%" />
</p>


## Technologies Used

- **Jetpack Compose**: Modern toolkit for building native Android UI.
- **The Movie DB API**: External API used to fetch movie data.
- **MVVM Architecture**: Design pattern for separation of concerns and maintainable code.
- **Repository Pattern**: Design pattern for abstracting data sources.
- **Kotlin**: Programming language used for Android app development.
- **Coroutines**: Asynchronous programming library for simplifying code that executes asynchronously.
- **Retrofit**: HTTP client for making network requests.
- **Room**: Persistence library for storing data locally.
- **DataStore**: Jetpack library for storing key-value pairs.
- **Hilt**: Dependency injection library for simplifying dependency injection.
- **Flow**: Asynchronous data stream that sequentially emits values.

## Lint

- **Ktlint**: Kotlin linter for enforcing Kotlin coding styles.


```
./gradlew ktlintCheck 
./gradlew ktlintFormat
```

## Getting Started

1. **Clone the repository**:

    ```
    git@github.com:andrii-zubenko/MovieApp.git
    ```

2. **Open in Android Studio**:

   Open the project in Android Studio and ensure you have the necessary dependencies installed.

3. **API Key**:

   Add your [The Movie DB](https://www.themoviedb.org/)'s API key in local.properties file.
   apiKey=00000000000000

4. **Run the app**:

   Run the app on an emulator or a physical device to explore the MovieApp functionality.
