package tmidev.themeswitch.domain.model

import tmidev.themeswitch.domain.type.ThemeStyleType

data class AppConfiguration(
    val useDynamicColors: Boolean,
    val themeStyle: ThemeStyleType
)