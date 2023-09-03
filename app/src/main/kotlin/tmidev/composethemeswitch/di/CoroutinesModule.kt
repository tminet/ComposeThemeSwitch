package tmidev.composethemeswitch.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tmidev.composethemeswitch.util.CoroutinesDispatchers
import tmidev.composethemeswitch.util.CoroutinesDispatchersImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface CoroutinesModule {
    @Binds
    @Singleton
    fun bindCoroutinesDispatchers(
        impl: CoroutinesDispatchersImpl
    ): CoroutinesDispatchers
}