package tmidev.composethemeswitch.ui.component.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val AppShapes = Shapes(
    extraSmall = RoundedCornerShape(size = 2.dp),
    small = RoundedCornerShape(size = 4.dp),
    medium = RoundedCornerShape(size = 8.dp),
    large = RoundedCornerShape(size = 16.dp),
    extraLarge = RoundedCornerShape(size = 32.dp)
)