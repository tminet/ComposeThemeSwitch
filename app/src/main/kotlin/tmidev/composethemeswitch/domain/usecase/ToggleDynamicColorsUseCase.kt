package tmidev.composethemeswitch.domain.usecase

import tmidev.composethemeswitch.data.UserPreferences
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