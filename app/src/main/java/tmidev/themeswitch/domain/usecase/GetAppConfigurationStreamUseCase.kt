package tmidev.themeswitch.domain.usecase

import kotlinx.coroutines.flow.Flow
import tmidev.themeswitch.data.UserPreferences
import tmidev.themeswitch.domain.model.AppConfiguration
import javax.inject.Inject

interface GetAppConfigurationStreamUseCase {
    suspend operator fun invoke(): Flow<AppConfiguration>
}

class GetAppConfigurationStreamUseCaseImpl @Inject constructor(
    private val userPreferences: UserPreferences
) : GetAppConfigurationStreamUseCase {
    override suspend fun invoke(): Flow<AppConfiguration> =
        userPreferences.appConfigurationStream
}