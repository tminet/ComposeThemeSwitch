package tmidev.composethemeswitch.domain.model

import tmidev.composethemeswitch.domain.type.ThemeStyleType

data class AppConfiguration(
    val useDynamicColors: Boolean,
    val themeStyle: ThemeStyleType
)