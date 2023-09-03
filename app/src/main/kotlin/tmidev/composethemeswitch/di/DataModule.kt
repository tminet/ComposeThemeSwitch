package tmidev.composethemeswitch.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tmidev.composethemeswitch.data.UserPreferences
import tmidev.composethemeswitch.data.UserPreferencesImplDataStore
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    @Singleton
    fun bindUserPreferences(
        impl: UserPreferencesImplDataStore
    ): UserPreferences
}