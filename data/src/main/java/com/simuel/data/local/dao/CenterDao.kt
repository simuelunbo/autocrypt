package com.simuel.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.simuel.data.local.entity.CenterEntity

@Dao
internal interface CenterDao {

    @Query("SELECT * FROM CenterEntity")
    suspend fun getCenters(): List<CenterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCenter(centerEntity: CenterEntity)


}