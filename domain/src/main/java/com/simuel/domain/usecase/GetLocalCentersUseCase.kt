package com.simuel.domain.usecase

import com.simuel.domain.model.Center
import com.simuel.domain.repository.CenterRepository
import javax.inject.Inject

class GetLocalCentersUseCase @Inject constructor(
    private val repository: CenterRepository
) {
    suspend operator fun invoke(): List<Center> {
        return repository.getLocalCenters()
    }
}