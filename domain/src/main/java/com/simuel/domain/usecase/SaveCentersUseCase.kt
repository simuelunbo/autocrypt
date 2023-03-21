package com.simuel.domain.usecase

import com.simuel.domain.model.Center
import com.simuel.domain.repository.CenterRepository
import javax.inject.Inject

class SaveCentersUseCase @Inject constructor(
    private val repository: CenterRepository
) {
    suspend operator fun invoke(center: Center) {
        return repository.insertCenter(center)
    }
}