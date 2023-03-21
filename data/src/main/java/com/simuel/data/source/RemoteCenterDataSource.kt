package com.simuel.data.source

import com.simuel.domain.model.Center

internal interface RemoteCenterDataSource {
    suspend fun getCenterList(
        page: Int
    ): List<Center>
}