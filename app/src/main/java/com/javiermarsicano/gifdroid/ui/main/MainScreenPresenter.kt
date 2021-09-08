package com.javiermarsicano.gifdroid.ui.main

import com.javiermarsicano.gifdroid.base.mvp.BaseMVPPresenter
import com.javiermarsicano.gifdroid.data.model.Content
import com.javiermarsicano.gifdroid.data.model.Favourite
import com.javiermarsicano.gifdroid.data.repository.FavouritesRepository
import com.javiermarsicano.gifdroid.data.repository.ImagesSearchRepository
import com.javiermarsicano.gifdroid.data.repository.TrendingRepository
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class MainScreenPresenter @Inject constructor(
     private val repository: TrendingRepository,
     private val favouritesRepository: FavouritesRepository,
     private val searchRepository: ImagesSearchRepository
): BaseMVPPresenter<MainScreenContract.View>(), MainScreenContract.Presenter {
    override fun getTrendingImages() {
        viewReference.get()?.showLoading()
        repository.getTrendingContent()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally { viewReference.get()?.hideLoading() }
            .subscribe(
                {
                    val view = viewReference.get()
                    view?.clearResults()
                    view?.addResults(it)
                },
                { viewReference.get()?.onError(it.message) }
            ).bindToLifecycle()
    }

    override fun setImageFavourite(content: Content) {
        Completable.fromAction {
            favouritesRepository.saveFavourite(
                Favourite(
                    content.id,
                    content.images.original.url
                )
            )
        }.subscribeOn(Schedulers.io())
            .subscribe {
                Timber.d("Image save as favourite: ${content.title}")
            }.bindToLifecycle()

    }

    override fun searchImages(query: String) {
        viewReference.get()?.showLoading()
        searchRepository.search(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally { viewReference.get()?.hideLoading() }
            .subscribe(
                {
                    val view = viewReference.get()
                    view?.clearResults()
                    view?.addResults(it)
                },
                { viewReference.get()?.onError(it.message) }
            ).bindToLifecycle()
    }

}