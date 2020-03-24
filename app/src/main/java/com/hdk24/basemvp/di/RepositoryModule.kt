package com.hdk24.basemvp.di

import com.hdk24.basemvp.data.repository.MovieRepositoryImpl
import com.hdk24.basemvp.domain.repository.MovieRepository
import org.koin.dsl.module

/*
 *  Created by Hanantadk on 22/03/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 22/03/20.
 */

val repositoryModule = module {

    factory<MovieRepository> { MovieRepositoryImpl(get(), get()) }
}
