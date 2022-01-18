package tmidev.themeswitch.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val LightColorPalette = lightColors(
    primary = PrimaryLightColor,
    primaryVariant = PrimaryVariantLightColor,
    onPrimary = OnPrimaryLightColor,
    secondary = SecondaryLightColor,
    secondaryVariant = SecondaryVariantLightColor,
    onSecondary = OnSecondaryLightColor,
    background = BackgroundLightColor,
    onBackground = OnBackgroundLightColor,
    surface = SurfaceLightColor,
    onSurface = OnSurfaceLightColor,
    error = ErrorColor,
    onError = OnErrorColor
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
    error = ErrorColor,
    onError = OnErrorColor
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette

    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = colors.primary
        )
    }

    CompositionLocalProvider(
        LocalSpacing provides Spacing(),
        LocalElevating provides Elevating()
    ) {
        MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}