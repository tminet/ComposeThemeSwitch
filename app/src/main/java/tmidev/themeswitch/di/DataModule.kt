package tmidev.themeswitch.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tmidev.themeswitch.data.UserPreferences
import tmidev.themeswitch.data.UserPreferencesImplDataStore

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun bindUserPreferences(
        userPreferences: UserPreferencesImplDataStore
    ): UserPreferences
}