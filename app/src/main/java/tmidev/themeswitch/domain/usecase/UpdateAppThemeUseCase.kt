package tmidev.themeswitch.domain.usecase

import tmidev.themeswitch.data.UserPreferences
import javax.inject.Inject

interface UpdateAppThemeUseCase {
    suspend operator fun invoke(darkMode: Boolean)
}

class UpdateAppThemeUseCaseImpl @Inject constructor(
    private val userPreferences: UserPreferences
) : UpdateAppThemeUseCase {
    override suspend fun invoke(darkMode: Boolean) =
        userPreferences.updateAppTheme(darkMode = darkMode)
}