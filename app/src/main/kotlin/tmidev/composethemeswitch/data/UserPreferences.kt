package tmidev.composethemeswitch.data

import kotlinx.coroutines.flow.Flow
import tmidev.composethemeswitch.domain.model.AppConfiguration
import tmidev.composethemeswitch.domain.type.ThemeStyleType

interface UserPreferences {
    val appConfigurationStream: Flow<AppConfiguration>

    suspend fun toggleDynamicColors()

    suspend fun changeThemeStyle(themeStyle: ThemeStyleType)
}