package com.simuel.data.local.source

import com.simuel.data.local.dao.CenterDao
import com.simuel.data.mapper.toCenterEntity
import com.simuel.data.mapper.toCenters
import com.simuel.data.source.LocalCenterDataSource
import com.simuel.domain.model.Center
import javax.inject.Inject

internal class LocalCenterDataSourceImpl @Inject constructor(
    private val dao: CenterDao
) : LocalCenterDataSource {
    override suspend fun getCenterList(): List<Center> {
        return dao.getCenters().map { it.toCenters() }
    }

    override suspend fun insertCenter(centers: Center) {
        val centerEntity = centers.toCenterEntity()
        dao.insertCenter(centerEntity)
    }
}