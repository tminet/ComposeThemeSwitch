package tmidev.composethemeswitch.util

import android.graphics.Color
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.annotation.ChecksSdkIntAtLeast
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import tmidev.composethemeswitch.domain.type.ThemeStyleType

/**
 * Check if this device is compatible with Dynamic Colors for Material 3.
 *
 * @return true when this device is API 31 (Android 12) or up, false otherwise.
 */
@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
fun isCompatibleWithDynamicColors(): Boolean =
    Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

/**
 * Set the edge to be transparent.
 *
 * @param darkMode when dark mode is enabled or not.
 */
fun ComponentActivity.transparentEdge(darkMode: Boolean) {
    fun systemBarStyle(
        darkMode: Boolean
    ): SystemBarStyle = if (darkMode) SystemBarStyle.dark(
        scrim = Color.TRANSPARENT
    ) else SystemBarStyle.light(
        scrim = Color.TRANSPARENT,
        darkScrim = Color.TRANSPARENT
    )

    enableEdgeToEdge(
        statusBarStyle = systemBarStyle(darkMode = darkMode),
        navigationBarStyle = systemBarStyle(darkMode = darkMode)
    )
}

/**
 * Map a ThemeStyleType into a [Boolean].
 *
 * @param themeStyle the [ThemeStyleType].
 *
 * @return the corresponding boolean value of this ThemeStyleType.
 */
@Composable
fun shouldUseDarkTheme(
    themeStyle: ThemeStyleType
): Boolean = when (themeStyle) {
    ThemeStyleType.FollowAndroidSystem -> isSystemInDarkTheme()
    ThemeStyleType.LightMode -> false
    ThemeStyleType.DarkMode -> true
}