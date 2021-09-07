package com.javiermarsicano.gifdroid.ui.favourite

import com.javiermarsicano.gifdroid.base.mvp.BaseMVPPresenter
import com.javiermarsicano.gifdroid.data.model.Favourite
import com.javiermarsicano.gifdroid.data.repository.FavouritesRepository
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class FavouriteScreenPresenter @Inject constructor(val repository: FavouritesRepository): BaseMVPPresenter<FavouriteScreenContract.View>(), FavouriteScreenContract.Presenter {
    override fun getFavourites() {
        repository.loadFavourites()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { viewReference.get()?.showFavourites(it) },
                { viewReference.get()?.onError(it.message) }
            ).bindToLifecycle()
    }

    override fun removeFavourite(favourite: Favourite) {
        Completable.fromAction {
            repository.deleteFavourite(favourite)
        }.andThen(repository.loadFavourites())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                {
                    viewReference.get()?.showFavourites(it)
                },
                { viewReference.get()?.onError(it.message) }
            ).bindToLifecycle()
    }

}