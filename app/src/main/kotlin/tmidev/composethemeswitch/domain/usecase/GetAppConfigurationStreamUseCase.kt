package tmidev.composethemeswitch.domain.usecase

import kotlinx.coroutines.flow.Flow
import tmidev.composethemeswitch.data.UserPreferences
import tmidev.composethemeswitch.domain.model.AppConfiguration
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