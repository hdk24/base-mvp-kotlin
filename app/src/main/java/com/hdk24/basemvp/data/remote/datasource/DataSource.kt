package com.hdk24.basemvp.data.remote.datasource

import com.hdk24.basemvp.data.remote.model.MovieModel
import io.reactivex.Single

/*
 *  Created by Hanantadk on 22/03/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 22/03/20.
 */

interface DataSource {
    fun discoverMovie(page: Int): Single<List<MovieModel>>
}
