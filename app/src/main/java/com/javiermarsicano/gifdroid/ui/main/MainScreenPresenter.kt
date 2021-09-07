package com.javiermarsicano.gifdroid.ui.main

import com.javiermarsicano.gifdroid.base.mvp.BaseMVPPresenter
import com.javiermarsicano.gifdroid.data.repository.TrendingRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainScreenPresenter @Inject constructor(
     private val repository: TrendingRepository
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
                    view?.addTrendingResults(it)
                },
                { viewReference.get()?.onError(it.message) }
            ).bindToLifecycle()
    }

}