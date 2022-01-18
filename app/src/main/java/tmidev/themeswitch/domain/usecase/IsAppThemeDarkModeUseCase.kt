package tmidev.themeswitch.domain.usecase

import kotlinx.coroutines.flow.Flow

interface IsAppThemeDarkModeUseCase {
    operator fun invoke(): Flow<Boolean?>
}