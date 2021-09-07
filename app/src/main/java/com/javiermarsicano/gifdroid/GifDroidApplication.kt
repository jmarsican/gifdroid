package com.javiermarsicano.gifdroid

import android.app.Application
import com.javiermarsicano.gifdroid.base.di.DaggerGifDroidComponent
import com.javiermarsicano.gifdroid.base.di.GifDroidComponent
import com.javiermarsicano.gifdroid.base.di.GifDroidModule
import timber.log.Timber

class GifDroidApplication: Application() {

    lateinit var component: GifDroidComponent

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        this.component = DaggerGifDroidComponent.builder().gifDroidModule(GifDroidModule(this)).build()

    }
}