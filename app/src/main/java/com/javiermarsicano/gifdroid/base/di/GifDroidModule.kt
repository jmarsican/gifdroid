package com.javiermarsicano.gifdroid.base.di

import android.app.Application
import android.net.Uri
import androidx.room.Room
import com.javiermarsicano.gifdroid.BuildConfig.BASE_URL
import com.javiermarsicano.gifdroid.data.persistence.db.LocalStorageDatabase
import com.javiermarsicano.gifdroid.data.repository.FavouritesLocalDBRepository
import com.javiermarsicano.gifdroid.data.repository.FavouritesRepository
import com.javiermarsicano.gifdroid.data.repository.ImagesSearchRemoteRepository
import com.javiermarsicano.gifdroid.data.repository.ImagesSearchRepository
import com.javiermarsicano.gifdroid.data.repository.TrendingRemoteRepository
import com.javiermarsicano.gifdroid.data.repository.TrendingRepository
import com.javiermarsicano.gifdroid.ui.favourite.FavouriteScreenContract
import com.javiermarsicano.gifdroid.ui.favourite.FavouriteScreenPresenter
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

    @Singleton
    @Provides
    fun provideFavouritesRepository(database: LocalStorageDatabase): FavouritesRepository =
        FavouritesLocalDBRepository(database)

    @Singleton
    @Provides
    fun providesSearchRepository(): ImagesSearchRepository = ImagesSearchRemoteRepository()

    @Singleton
    @Provides
    fun provideDatabase(): LocalStorageDatabase =
        Room.databaseBuilder(application, LocalStorageDatabase::class.java, "gifsdroid.db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideMainPresenter(presenter: MainScreenPresenter):
            MainScreenContract.Presenter = presenter

    @Provides
    fun provideFavouritePresenter(presenter: FavouriteScreenPresenter):
            FavouriteScreenContract.Presenter = presenter
}