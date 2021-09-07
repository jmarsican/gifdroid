package com.javiermarsicano.gifdroid.data.persistence.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.javiermarsicano.gifdroid.data.persistence.entity.ImageEntity

@Database(entities = [ImageEntity::class], version = 1, exportSchema = false)
abstract class LocalStorageDatabase: RoomDatabase() {
    abstract fun imageEntityDao(): ImageEntityDao
}