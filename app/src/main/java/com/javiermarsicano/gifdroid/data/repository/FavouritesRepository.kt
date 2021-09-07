package com.javiermarsicano.gifdroid.data.repository

import com.javiermarsicano.gifdroid.data.model.Favourite
import io.reactivex.Single

interface FavouritesRepository {
    fun loadFavourites(): Single<List<Favourite>>
}