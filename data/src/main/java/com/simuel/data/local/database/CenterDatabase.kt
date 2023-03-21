package com.simuel.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.simuel.data.local.dao.CenterDao
import com.simuel.data.local.entity.CenterEntity

@Database(entities = [CenterEntity::class], version = 1)
internal abstract class CenterDatabase : RoomDatabase() {
    abstract fun localDao(): CenterDao
}