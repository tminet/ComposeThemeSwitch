package tmidev.composethemeswitch.ui.component.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import tmidev.composethemeswitch.util.isCompatibleWithDynamicColors

/**
 * App theme.
 *
 * @param useDarkTheme when the theme should have dark colors. Default follow the Android theme.
 * @param useDynamicColors when the color scheme should use colors based on user's wallpaper,
 * this only works for api 31 and up. Default is true.
 * @param content the content for this theme.
 */
@Composable
fun AppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    useDynamicColors: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        isCompatibleWithDynamicColors() && useDynamicColors -> {
            val context = LocalContext.current
            if (useDarkTheme) dynamicDarkColorScheme(context = context)
            else dynamicLightColorScheme(context = context)
        }

        useDarkTheme -> darkColorScheme()

        else -> lightColorScheme()
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        shapes = AppShapes,
        content = {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = colorScheme.background,
                contentColor = colorScheme.onBackground,
                content = content
            )
        }
    )
}