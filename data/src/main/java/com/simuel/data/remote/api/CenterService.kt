package com.simuel.data.remote.api

import com.simuel.data.remote.model.CentersResponse
import retrofit2.http.GET
import retrofit2.http.Query

internal interface CenterService {
    @GET("api/15077586/v1/centers")
    suspend fun getCenterList(
        @Query("page") page: Int,
        @Query("perPage") perPage: Int = 10,
        @Query("serviceKey") serviceKey: String = "bNmSjmL3NWL/mAmsQV0SyDT+8DCdZckhVg5/tSsmJHa47eBZBE+aFvCHYxeM1Dsz2FcgQ64elqYL3mr6GUyjOg=="
    ): CentersResponse
}