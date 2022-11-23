package tmidev.themeswitch.data

import kotlinx.coroutines.flow.Flow
import tmidev.themeswitch.domain.model.AppConfiguration
import tmidev.themeswitch.domain.type.ThemeStyleType

interface UserPreferences {
    val appConfigurationStream: Flow<AppConfiguration>

    suspend fun toggleDynamicColors()

    suspend fun changeThemeStyle(themeStyle: ThemeStyleType)
}