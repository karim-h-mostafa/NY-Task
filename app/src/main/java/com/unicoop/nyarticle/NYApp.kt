package com.unicoop.nyarticle

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class NYApp : Application() {
    @Inject
    lateinit var timber: Timber.Tree
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(timber)
        }
    }

}