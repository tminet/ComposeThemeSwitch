package tmidev.themeswitch.util

import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import tmidev.themeswitch.domain.type.ThemeStyleType

/**
 * Check if this device is compatible with Dynamic Colors for Material 3.
 *
 * @return true when this device is API 31 (Android 12) or up, false otherwise.
 */
@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
fun isCompatibleWithDynamicColors(): Boolean =
    Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

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