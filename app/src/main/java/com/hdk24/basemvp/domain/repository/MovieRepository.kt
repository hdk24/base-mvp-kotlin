package com.hdk24.basemvp.domain.repository

import com.hdk24.basemvp.presentation.model.Movie
import io.reactivex.Single

/*
 *  Created by Hanantadk on 22/03/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 22/03/20.
 */
interface MovieRepository {

    fun fetchMovie(page: Int): Single<List<Movie>>

    fun getCacheMovie(): Single<List<Movie>>
}
