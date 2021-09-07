package com.javiermarsicano.gifdroid.base.di

import com.javiermarsicano.gifdroid.data.repository.TrendingRemoteRepository
import com.javiermarsicano.gifdroid.data.repository.TrendingRepository
import com.javiermarsicano.gifdroid.ui.main.MainScreenContract
import com.javiermarsicano.gifdroid.ui.main.MainScreenPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class GifDroidModule {
    @Singleton
    @Provides
    fun provideTrendingRepository(): TrendingRepository = TrendingRemoteRepository()

    @Provides
    fun provideEventsPresenter(presenter: MainScreenPresenter):
            MainScreenContract.Presenter = presenter
}