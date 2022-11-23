package tmidev.themeswitch.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import tmidev.themeswitch.domain.usecase.ChangeThemeStyleUseCase
import tmidev.themeswitch.domain.usecase.ChangeThemeStyleUseCaseImpl
import tmidev.themeswitch.domain.usecase.GetAppConfigurationStreamUseCase
import tmidev.themeswitch.domain.usecase.GetAppConfigurationStreamUseCaseImpl
import tmidev.themeswitch.domain.usecase.ToggleDynamicColorsUseCase
import tmidev.themeswitch.domain.usecase.ToggleDynamicColorsUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {
    @Binds
    fun bindGetAppConfigurationStreamUseCase(
        useCase: GetAppConfigurationStreamUseCaseImpl
    ): GetAppConfigurationStreamUseCase

    @Binds
    fun bindToggleDynamicColorsUseCase(
        useCase: ToggleDynamicColorsUseCaseImpl
    ): ToggleDynamicColorsUseCase

    @Binds
    fun bindChangeThemeStyleUseCase(
        useCase: ChangeThemeStyleUseCaseImpl
    ): ChangeThemeStyleUseCase
}