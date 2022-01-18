# Compose Theme Switch

Example Android project built with `Kotlin` and `Compose`</br>

- This project is integrated with `Hilt` to provide Dependency Injection
- The `Preferences Datastore` library is present to save current <b>app theme</b> and maintain data persistence
- `Navigation Component` is pre-configured
- The `Accompanist System UI Controller` is also included to assist with color change of <b>Status Bar</b> and <b>Navigation Bar</b>

Note that the Material Icon library [androidx.compose.material:material-icons-extended](https://mvnrepository.com/artifact/androidx.compose.material/material-icons-extended) is present in this project.
And because of that the size of the app becomes <b>considerably large</b>.
To use it in Production, [code shrinking](https://developer.android.com/studio/build/shrink-code#shrink-code) it is <b>highly recommended</b>.
