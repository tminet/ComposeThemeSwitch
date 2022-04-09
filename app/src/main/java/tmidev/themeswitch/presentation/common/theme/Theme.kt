package tmidev.themeswitch.presentation.common.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import tmidev.themeswitch.R
import tmidev.themeswitch.presentation.common.AppLoadingAnimation

private val LightColorPalette = lightColors(
    primary = PrimaryLightColor,
    onPrimary = OnPrimaryLightColor,
    primaryVariant = PrimaryVariantLightColor,
    secondary = SecondaryLightColor,
    onSecondary = OnSecondaryLightColor,
    secondaryVariant = SecondaryVariantLightColor,
    background = BackgroundLightColor,
    onBackground = OnBackgroundLightColor,
    surface = SurfaceLightColor,
    onSurface = OnSurfaceLightColor,
    error = ErrorLightColor,
    onError = OnErrorLightColor
)

private val DarkColorPalette = darkColors(
    primary = PrimaryDarkColor,
    onPrimary = OnPrimaryDarkColor,
    primaryVariant = PrimaryVariantDarkColor,
    secondary = SecondaryDarkColor,
    onSecondary = OnSecondaryDarkColor,
    secondaryVariant = SecondaryVariantDarkColor,
    background = BackgroundDarkColor,
    onBackground = OnBackgroundDarkColor,
    surface = SurfaceDarkColor,
    onSurface = OnSurfaceDarkColor,
    error = ErrorDarkColor,
    onError = OnErrorDarkColor
)

private val SplashColorPalette = darkColors(
    primary = PrimaryDarkColor,
    onPrimary = OnPrimaryDarkColor,
    primaryVariant = PrimaryVariantDarkColor,
    secondary = SecondaryDarkColor,
    onSecondary = OnSecondaryDarkColor,
    secondaryVariant = SecondaryVariantDarkColor,
    background = BackgroundDarkColor,
    onBackground = OnBackgroundDarkColor,
    surface = SurfaceDarkColor,
    onSurface = OnSurfaceDarkColor,
    error = ErrorDarkColor,
    onError = OnErrorDarkColor
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setSystemBarsColor(color = colors.primary)
        systemUiController.systemBarsDarkContentEnabled = false
    }

    CompositionLocalProvider(
        LocalSpacing provides Spacing(),
        LocalElevating provides Elevating()
    ) {
        MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    content()
                }
            }
        )
    }
}

@Composable
fun SplashTheme() {
    val colors = SplashColorPalette
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setSystemBarsColor(color = Color.Transparent)
        systemUiController.systemBarsDarkContentEnabled = false
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colors.background),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AppLoadingAnimation()

                Spacer(modifier = Modifier.size(16.dp))

                Text(
                    text = stringResource(id = R.string.appName),
                    color = MaterialTheme.colors.onBackground,
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    )
}