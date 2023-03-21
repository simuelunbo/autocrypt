package com.simuel.data.mapper

import com.simuel.data.local.entity.CenterEntity
import com.simuel.data.remote.model.CenterResponse
import com.simuel.domain.model.Center

internal fun CenterResponse.toCenters(): Center {
    return Center(
        id = this.id,
        centerName = this.centerName,
        sido = this.sido,
        sigungu = this.sigungu,
        facilityName = this.facilityName,
        zipCode = this.zipCode,
        address = this.address,
        lat = this.lat,
        lng = this.lng,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        centerType = this.centerType,
        org = this.org,
        phoneNumber = this.phoneNumber
    )
}

internal fun CenterEntity.toCenters(): Center {
    return Center(
        id = this.id,
        centerName = this.centerName,
        sido = this.sido,
        sigungu = this.sigungu,
        facilityName = this.facilityName,
        zipCode = this.zipCode,
        address = this.address,
        lat = this.lat,
        lng = this.lng,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        centerType = this.centerType,
        org = this.org,
        phoneNumber = this.phoneNumber
    )
}

internal fun Center.toCenterEntity(): CenterEntity {
    return CenterEntity(
        id = this.id,
        centerName = this.centerName,
        sido = this.sido,
        sigungu = this.sigungu,
        facilityName = this.facilityName,
        zipCode = this.zipCode,
        address = this.address,
        lat = this.lat,
        lng = this.lng,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        centerType = this.centerType,
        org = this.org,
        phoneNumber = this.phoneNumber
    )
}