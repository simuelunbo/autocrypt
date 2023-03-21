package com.simuel.data.remote.source

import com.simuel.data.mapper.toCenters
import com.simuel.data.remote.api.CenterService
import com.simuel.data.source.RemoteCenterDataSource
import com.simuel.domain.model.Center
import javax.inject.Inject

internal class RemoteCenterDataSourceImpl @Inject constructor(
    private val service: CenterService
) : RemoteCenterDataSource {
    override suspend fun getCenterList(page: Int): List<Center> {
        return service.getCenterList(page).data.map {
            it.toCenters()
        }
    }
}