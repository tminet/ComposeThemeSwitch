package tmidev.themeswitch.presentation.common

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun AppLoadingAnimation(
    modifier: Modifier = Modifier,
    circleSize: Dp = 20.dp,
    spaceBetween: Dp = 10.dp,
    travelDistance: Dp = 15.dp,
    circleColor: Color = MaterialTheme.colors.primary
) {
    val circles = listOf(
        remember { Animatable(initialValue = 0F) },
        remember { Animatable(initialValue = 0F) },
        remember { Animatable(initialValue = 0F) }
    )

    circles.forEachIndexed { index, animation ->
        LaunchedEffect(key1 = animation) {
            delay(timeMillis = index * 100L)
            animation.animateTo(
                targetValue = 1F,
                animationSpec = infiniteRepeatable(
                    animation = keyframes {
                        durationMillis = 1200
                        0.0f at 0 with LinearOutSlowInEasing
                        1.0f at 300 with LinearOutSlowInEasing
                        0.0f at 600 with LinearOutSlowInEasing
                        0.0f at 1200 with LinearOutSlowInEasing
                    },
                    repeatMode = RepeatMode.Restart
                )
            )
        }
    }

    val circlesValue = circles.map { it.value }

    val distance = LocalDensity.current.run { travelDistance.toPx() }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(spaceBetween)
    ) {
        circlesValue.forEach { value ->
            Box(modifier = Modifier
                .size(size = circleSize)
                .graphicsLayer {
                    translationY = -value * distance
                }
                .background(
                    color = circleColor,
                    shape = CircleShape
                )
            )
        }
    }
}