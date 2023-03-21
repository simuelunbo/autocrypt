package com.simuel.data.di

import com.simuel.data.local.source.LocalCenterDataSourceImpl
import com.simuel.data.source.LocalCenterDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class LocalDataSourceModule {
    @Binds
    @Singleton
    abstract fun bindLocalCenterDataSource(source: LocalCenterDataSourceImpl): LocalCenterDataSource
}