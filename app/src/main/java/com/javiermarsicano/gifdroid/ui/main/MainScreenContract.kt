package com.javiermarsicano.gifdroid.ui.main

import com.javiermarsicano.gifdroid.base.mvp.MVPPresenter
import com.javiermarsicano.gifdroid.base.mvp.MVPView
import com.javiermarsicano.gifdroid.data.model.Content

interface MainScreenContract {
    interface View: MVPView {
    }

    interface Presenter: MVPPresenter<View> {
        fun getTrendingImages()
    }
}