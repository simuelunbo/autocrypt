package com.simuel.data.di

import com.simuel.data.repository.CenterRepositoryImpl
import com.simuel.domain.repository.CenterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCenterRepository(repository: CenterRepositoryImpl): CenterRepository
}