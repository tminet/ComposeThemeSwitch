package tmidev.themeswitch.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import tmidev.themeswitch.domain.usecase.IsAppThemeDarkModeUseCase
import tmidev.themeswitch.domain.usecase.IsAppThemeDarkModeUseCaseImpl
import tmidev.themeswitch.domain.usecase.UpdateAppThemeUseCase
import tmidev.themeswitch.domain.usecase.UpdateAppThemeUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {
    @Binds
    fun bindIsAppThemeDarkModeUseCase(
        useCase: IsAppThemeDarkModeUseCaseImpl
    ): IsAppThemeDarkModeUseCase

    @Binds
    fun bindUpdateAppThemeUseCase(
        useCase: UpdateAppThemeUseCaseImpl
    ): UpdateAppThemeUseCase
}