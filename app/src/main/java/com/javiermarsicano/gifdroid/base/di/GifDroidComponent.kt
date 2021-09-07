package com.javiermarsicano.gifdroid.base.di

import com.javiermarsicano.gifdroid.ui.favourite.FavouriteFragment
import com.javiermarsicano.gifdroid.ui.main.MainFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [GifDroidModule::class])
interface GifDroidComponent {
    fun inject(fragment: MainFragment)
    fun inject(fragment: FavouriteFragment)
}