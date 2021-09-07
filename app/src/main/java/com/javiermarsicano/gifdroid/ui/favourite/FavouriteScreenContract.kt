package com.javiermarsicano.gifdroid.ui.favourite

import com.javiermarsicano.gifdroid.base.mvp.MVPPresenter
import com.javiermarsicano.gifdroid.base.mvp.MVPView
import com.javiermarsicano.gifdroid.data.model.Favourite

interface FavouriteScreenContract {
    interface View: MVPView {
        fun showFavourites(favourites: List<Favourite>)
    }

    interface Presenter: MVPPresenter<View> {
        fun getFavourites()
        fun removeFavourite(id: String)
    }
}