package tmidev.themeswitch.domain.usecase

import tmidev.themeswitch.data.UserPreferences
import javax.inject.Inject

interface ToggleDynamicColorsUseCase {
    suspend operator fun invoke()
}

class ToggleDynamicColorsUseCaseImpl @Inject constructor(
    private val userPreferences: UserPreferences
) : ToggleDynamicColorsUseCase {
    override suspend fun invoke() =
        userPreferences.toggleDynamicColors()
}