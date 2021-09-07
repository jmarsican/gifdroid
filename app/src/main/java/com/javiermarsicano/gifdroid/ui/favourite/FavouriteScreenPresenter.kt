package com.javiermarsicano.gifdroid.ui.favourite

import com.javiermarsicano.gifdroid.base.mvp.BaseMVPPresenter
import com.javiermarsicano.gifdroid.data.repository.FavouritesRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FavouriteScreenPresenter(val repository: FavouritesRepository): BaseMVPPresenter<FavouriteScreenContract.View>(), FavouriteScreenContract.Presenter {
    override fun getFavourites() {
        repository.loadFavourites()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { viewReference.get()?.showFavourites(it) },
                { viewReference.get()?.onError(it.message) }
            ).bindToLifecycle()
    }

    override fun removeFavourite(id: String) {
        TODO("Not yet implemented")
    }

}