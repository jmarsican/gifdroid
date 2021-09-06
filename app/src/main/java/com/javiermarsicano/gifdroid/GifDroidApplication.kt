package com.javiermarsicano.gifdroid

import android.app.Application
import timber.log.Timber

class GifDroidApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

    }
}