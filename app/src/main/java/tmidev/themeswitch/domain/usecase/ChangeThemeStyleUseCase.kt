package tmidev.themeswitch.domain.usecase

import tmidev.themeswitch.data.UserPreferences
import tmidev.themeswitch.domain.type.ThemeStyleType
import javax.inject.Inject

interface ChangeThemeStyleUseCase {
    suspend operator fun invoke(themeStyle: ThemeStyleType)
}

class ChangeThemeStyleUseCaseImpl @Inject constructor(
    private val userPreferences: UserPreferences
) : ChangeThemeStyleUseCase {
    override suspend fun invoke(themeStyle: ThemeStyleType) =
        userPreferences.changeThemeStyle(themeStyle = themeStyle)
}