package com.simuel.domain.repository

import com.simuel.domain.model.Center

interface CenterRepository {
    suspend fun getRemoteCenters(page: Int): List<Center>
    suspend fun insertCenter(center: Center)
    suspend fun getLocalCenters(): List<Center>
}