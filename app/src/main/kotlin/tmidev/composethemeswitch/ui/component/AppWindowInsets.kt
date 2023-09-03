package tmidev.composethemeswitch.ui.component

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.systemBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * [WindowInsets] that includes all [system bars][systemBars], [display cutout][displayCutout],
 * and [soft keyboard][ime].
 *
 * @param topExtraDp the extra value in [Dp] to apply in top. Default is 0.
 * @param bottomExtraDp the extra value in [Dp] to apply in bottom. Default is 0.
 * @param leftExtraDp the extra value in [Dp] to apply in left. Default is 0.
 * @param rightExtraDp the extra value in [Dp] to apply in right. Default is 0.
 */
@Composable
fun appWindowInsets(
    topExtraDp: Dp = 0.dp,
    bottomExtraDp: Dp = 0.dp,
    leftExtraDp: Dp = 0.dp,
    rightExtraDp: Dp = 0.dp
): WindowInsets {
    val windowInsets = WindowInsets.safeDrawing
    val layoutDirection = LocalLayoutDirection.current

    val top = windowInsets.asPaddingValues().calculateTopPadding()
    val bottom = windowInsets.asPaddingValues().calculateBottomPadding()
    val left = windowInsets.asPaddingValues().calculateLeftPadding(layoutDirection)
    val right = windowInsets.asPaddingValues().calculateRightPadding(layoutDirection)

    return WindowInsets(
        left = left + leftExtraDp,
        top = top + topExtraDp,
        right = right + rightExtraDp,
        bottom = bottom + bottomExtraDp
    )
}