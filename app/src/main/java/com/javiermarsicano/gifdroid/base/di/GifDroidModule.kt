package com.javiermarsicano.gifdroid.base.di

import android.app.Application
import android.net.Uri
import com.javiermarsicano.gifdroid.BuildConfig.BASE_URL
import com.javiermarsicano.gifdroid.data.repository.TrendingRemoteRepository
import com.javiermarsicano.gifdroid.data.repository.TrendingRepository
import com.javiermarsicano.gifdroid.ui.main.MainScreenContract
import com.javiermarsicano.gifdroid.ui.main.MainScreenPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class GifDroidModule(private val application: Application) {
    @Singleton
    @Provides
    fun provideTrendingRepository(): TrendingRepository = TrendingRemoteRepository(application, Uri.parse(BASE_URL))

    @Provides
    fun provideEventsPresenter(presenter: MainScreenPresenter):
            MainScreenContract.Presenter = presenter
}