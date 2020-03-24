package com.hdk24.basemvp

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.hdk24.basemvp.di.dataModule
import com.hdk24.basemvp.di.networkModule
import com.hdk24.basemvp.di.presenterModule
import com.hdk24.basemvp.di.repositoryModule
import com.hdk24.basemvp.utils.AppLogger
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/*
 *  Created by Hanantadk on 22/03/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 22/03/20.
 */
class App : Application() {

    /**
     * install multiDex on attachBaseContext
     * @param base context
     */
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    /**
     * init all process when apps started
     */
    override fun onCreate() {
        super.onCreate()

        // init timber
        AppLogger.init()

        // init koin injection
        startKoin {
            if (BuildConfig.DEBUG) androidLogger()
            androidContext(this@App)
            modules(listOf(dataModule, networkModule, repositoryModule, presenterModule))
        }
    }
}
