# Compose Theme Switch

Android project built with `Kotlin` and `Compose`.</br>

### Current features:

- `Hilt` for Dependency Injection
- `Navigation Component`
- `Preferences Datastore` to save current <b>app theme</b> and maintain data persistence
- `Accompanist System UI Controller` to assist with color change of <b>Status Bar</b> and <b>Navigation Bar</b>

Note that the [Extended Material Icons](https://mvnrepository.com/artifact/androidx.compose.material/material-icons-extended) library is present in this project.
And because of that the size of the app becomes <b>considerably large</b>.
To use it in Production, [code shrinking](https://developer.android.com/studio/build/shrink-code#shrink-code) it is <b>highly recommended</b>.
