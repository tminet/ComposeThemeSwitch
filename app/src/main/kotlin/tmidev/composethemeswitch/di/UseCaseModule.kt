package tmidev.composethemeswitch.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import tmidev.composethemeswitch.domain.usecase.ChangeThemeStyleUseCase
import tmidev.composethemeswitch.domain.usecase.ChangeThemeStyleUseCaseImpl
import tmidev.composethemeswitch.domain.usecase.GetAppConfigurationStreamUseCase
import tmidev.composethemeswitch.domain.usecase.GetAppConfigurationStreamUseCaseImpl
import tmidev.composethemeswitch.domain.usecase.ToggleDynamicColorsUseCase
import tmidev.composethemeswitch.domain.usecase.ToggleDynamicColorsUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {
    @Binds
    @ViewModelScoped
    fun bindGetAppConfigurationStreamUseCase(
        impl: GetAppConfigurationStreamUseCaseImpl
    ): GetAppConfigurationStreamUseCase

    @Binds
    @ViewModelScoped
    fun bindToggleDynamicColorsUseCase(
        impl: ToggleDynamicColorsUseCaseImpl
    ): ToggleDynamicColorsUseCase

    @Binds
    @ViewModelScoped
    fun bindChangeThemeStyleUseCase(
        impl: ChangeThemeStyleUseCaseImpl
    ): ChangeThemeStyleUseCase
}