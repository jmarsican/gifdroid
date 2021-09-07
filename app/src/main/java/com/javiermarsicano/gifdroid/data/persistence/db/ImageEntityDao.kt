package com.javiermarsicano.gifdroid.data.persistence.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.javiermarsicano.gifdroid.data.persistence.entity.ImageEntity
import io.reactivex.Single

@Dao
interface ImageEntityDao {

    @Query("SELECT * FROM favourite")
    fun getFavourites(): Single<List<ImageEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveFavourite(favourite: ImageEntity)

    @Delete
    fun deleteFavourite(favourite: ImageEntity)
}