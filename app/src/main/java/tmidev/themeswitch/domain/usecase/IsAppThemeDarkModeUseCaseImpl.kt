package tmidev.themeswitch.domain.usecase

import kotlinx.coroutines.flow.Flow
import tmidev.themeswitch.data.UserPreferences
import javax.inject.Inject

class IsAppThemeDarkModeUseCaseImpl @Inject constructor(
    private val userPreferences: UserPreferences
) : IsAppThemeDarkModeUseCase {
    override fun invoke(): Flow<Boolean?> =
        userPreferences.isAppThemeDarkMode
}