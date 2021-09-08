package com.javiermarsicano.gifdroid.ui.main

import com.javiermarsicano.gifdroid.base.mvp.BaseMVPPresenter
import com.javiermarsicano.gifdroid.data.model.Content
import com.javiermarsicano.gifdroid.data.model.Favourite
import com.javiermarsicano.gifdroid.data.repository.FavouritesRepository
import com.javiermarsicano.gifdroid.data.repository.ImagesSearchRepository
import com.javiermarsicano.gifdroid.data.repository.TrendingRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.Observables
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
        Observables.combineLatest(
            repository.getTrendingContent().toObservable(),
            favouritesRepository.loadFavourites().toObservable()
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally { viewReference.get()?.hideLoading() }
            .subscribe(
                { (results, favourites) ->
                    setResultsFavourites(favourites, results)
                    val view = viewReference.get()
                    view?.clearResults()
                    view?.addResults(results)
                },
                { viewReference.get()?.onError(it.message) }
            ).bindToLifecycle()
    }

    private fun setResultsFavourites(favourites: List<Favourite>, results: List<Content>) {
        favourites.forEach { favourite ->
            results.firstOrNull { it.id == favourite.id }?.isFavourite = true
        }
    }

    override fun setImageFavourite(content: Content) {
        val favourite = Favourite(
            content.id,
            content.images.original.url
        )
        Completable.fromAction {
            if (!content.isFavourite) {
                favouritesRepository.saveFavourite(favourite)
            } else {
                favouritesRepository.deleteFavourite(favourite)
            }
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                content.isFavourite = !content.isFavourite
                Timber.d("Image is favourite: ${content.isFavourite}")
                viewReference.get()?.updateFavourite(content)
            }.bindToLifecycle()
    }

    override fun searchImages(query: String) {
        viewReference.get()?.showLoading()
        Observables.combineLatest(
            searchRepository.search(query).toObservable(),
            favouritesRepository.loadFavourites().toObservable()
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally { viewReference.get()?.hideLoading() }
            .subscribe(
                { (results, favourites) ->
                    setResultsFavourites(favourites, results)
                    val view = viewReference.get()
                    view?.clearResults()
                    view?.addResults(results)
                },
                { viewReference.get()?.onError(it.message) }
            ).bindToLifecycle()
    }

}