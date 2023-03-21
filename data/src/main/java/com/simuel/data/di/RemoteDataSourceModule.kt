package com.simuel.data.di

import com.simuel.data.remote.source.RemoteCenterDataSourceImpl
import com.simuel.data.source.RemoteCenterDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RemoteDataSourceModule {

    @Binds
    @Singleton
    abstract fun bindRemoteCenterDataSource(source: RemoteCenterDataSourceImpl): RemoteCenterDataSource
}