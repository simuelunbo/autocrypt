package com.simuel.data.source

import com.simuel.domain.model.Center

internal interface LocalCenterDataSource {
    suspend fun getCenterList(): List<Center>

    suspend fun insertCenter(center: Center)
}