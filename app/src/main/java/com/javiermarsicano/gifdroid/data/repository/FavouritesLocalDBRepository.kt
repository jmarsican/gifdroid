package com.javiermarsicano.gifdroid.data.repository

import androidx.room.RoomDatabase
import com.javiermarsicano.gifdroid.data.model.Favourite
import com.javiermarsicano.gifdroid.data.persistence.db.LocalStorageDatabase
import com.javiermarsicano.gifdroid.data.persistence.entity.ImageEntity
import io.reactivex.Single

class FavouritesLocalDBRepository(private val database: LocalStorageDatabase) : FavouritesRepository {
    override fun loadFavourites(): Single<List<Favourite>> {
        return database.imageEntityDao().getFavourites().map { response ->
            response.map { it.toFavourite() }
        }
    }

    override fun saveFavourite(favourite: Favourite) {
        database.imageEntityDao().saveFavourite(ImageEntity(favourite.id, favourite.url))
    }

    fun ImageEntity.toFavourite() =
        Favourite(
            this.id,
            this.url
        )
}