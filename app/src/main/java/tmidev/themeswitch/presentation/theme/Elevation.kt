package tmidev.themeswitch.presentation.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Elevating(
    val none: Dp = 0.dp,
    val low: Dp = 4.dp,
    val medium: Dp = 6.dp,
    val high: Dp = 8.dp,
)

val LocalElevating = compositionLocalOf { Elevating() }

val MaterialTheme.elevating: Elevating
    @Composable
    @ReadOnlyComposable
    get() = LocalElevating.current