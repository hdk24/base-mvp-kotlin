package com.hdk24.basemvp.di

import androidx.room.Room
import com.hdk24.basemvp.data.local.AppDatabase
import com.hdk24.basemvp.data.preference.PrefHelperImpl
import com.hdk24.basemvp.data.remote.datasource.DataSource
import com.hdk24.basemvp.data.remote.datasource.DataSourceImpl
import com.hdk24.basemvp.domain.preference.PrefHelper
import com.hdk24.basemvp.presentation.base.AppSchedulerProvider
import com.hdk24.basemvp.presentation.base.SchedulerProvider
import org.koin.dsl.module

/*
 *  Created by Hanantadk on 22/03/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 22/03/20.
 */

// single definitions use -> long live components, never dropped
val dataModule = module {

    single<PrefHelper> { PrefHelperImpl(get()) }

    single<DataSource> { DataSourceImpl(get()) }

    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, AppDatabase.DB_NAME)
            .build()
    }

    single(createdAtStart = false) { get<AppDatabase>().movieDao() }

    single<SchedulerProvider> { AppSchedulerProvider() }
}
