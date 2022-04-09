package tmidev.themeswitch.domain.usecase

import tmidev.themeswitch.data.UserPreferences
import javax.inject.Inject

class UpdateAppThemeUseCaseImpl @Inject constructor(
    private val userPreferences: UserPreferences
) : UpdateAppThemeUseCase {
    override suspend fun invoke(darkMode: Boolean) =
        userPreferences.updateAppTheme(darkMode = darkMode)
}