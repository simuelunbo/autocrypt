package com.simuel.domain.usecase

import com.simuel.domain.model.Center
import com.simuel.domain.repository.CenterRepository
import javax.inject.Inject

class GetRemoteCentersUseCase @Inject constructor(
    private val repository: CenterRepository
) {
    suspend operator fun invoke(page: Int): List<Center> {
        return repository.getRemoteCenters(page)
    }
}