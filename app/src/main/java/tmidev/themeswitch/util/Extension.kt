package tmidev.themeswitch.util

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import tmidev.themeswitch.domain.type.ThemeStyleType

fun isCompatibleWithDynamicColors(): Boolean =
    Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

@Composable
fun shouldUseDarkTheme(
    themeStyle: ThemeStyleType
): Boolean = when (themeStyle) {
    ThemeStyleType.FollowAndroidSystem -> isSystemInDarkTheme()
    ThemeStyleType.LightMode -> false
    ThemeStyleType.DarkMode -> true
}