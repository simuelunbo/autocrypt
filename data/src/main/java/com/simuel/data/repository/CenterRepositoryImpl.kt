package com.simuel.data.repository

import com.simuel.data.source.LocalCenterDataSource
import com.simuel.data.source.RemoteCenterDataSource
import com.simuel.domain.model.Center
import com.simuel.domain.repository.CenterRepository
import javax.inject.Inject

internal class CenterRepositoryImpl @Inject constructor(
    private val local : LocalCenterDataSource,
    private val remote: RemoteCenterDataSource
) : CenterRepository{
    override suspend fun getRemoteCenters(page: Int): List<Center> {
        return remote.getCenterList(page)
    }

    override suspend fun insertCenter(center: Center)  {
        local.insertCenter(center)
    }

    override suspend fun getLocalCenters(): List<Center> {
        return local.getCenterList()
    }
}