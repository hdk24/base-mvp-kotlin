package com.hdk24.basemvp.di

import com.hdk24.basemvp.presentation.presenter.MoviePresenter
import org.koin.dsl.module

/*
 *  Created by Hanantadk on 22/03/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 22/03/20.
 */

val presenterModule = module {
    // factory definition use for medium/short live component
    // presenter must be created each time so used factory definitions
    factory { MoviePresenter(get(), get()) }
}
