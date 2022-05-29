package tmidev.themeswitch.presentation.common.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Elevating(
    val none: Dp = 0.dp,
    val extraLow: Dp = 2.dp,
    val low: Dp = 4.dp,
    val medium: Dp = 8.dp,
    val high: Dp = 16.dp,
    val extraHigh: Dp = 32.dp
)

val LocalElevating = compositionLocalOf { Elevating() }

/**
 * - none: 0dp
 * - extra low: 2dp
 * - low: 4dp
 * - medium: 8dp
 * - high: 16dp
 * - extra high: 32dp
 */
val MaterialTheme.elevating: Elevating
    @Composable
    @ReadOnlyComposable
    get() = LocalElevating.current