package com.javiermarsicano.gifdroid.data.repository

import android.content.Context
import android.net.Uri
import com.javiermarsicano.gifdroid.data.model.Favourite
import io.reactivex.Single

class FavouritesLocalDBRepository(context: Context) : FavouritesRepository {
    override fun loadFavourites(): Single<List<Favourite>> {
        return Single.just(listOf())
    }
}