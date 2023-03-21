package com.simuel.domain.usecase

import javax.inject.Inject

data class CenterUseCase @Inject constructor(
    val getRemoteCenter: GetRemoteCentersUseCase,
    val getLocalCenter: GetLocalCentersUseCase,
    val saveCenter: SaveCentersUseCase
)