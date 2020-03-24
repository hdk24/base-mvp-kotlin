package com.hdk24.basemvp.data.remote.datasource

import com.hdk24.basemvp.BuildConfig
import com.hdk24.basemvp.data.remote.api.MovieAPI
import com.hdk24.basemvp.data.remote.base.ErrorNetworkHandler
import com.hdk24.basemvp.data.remote.model.MovieModel
import io.reactivex.Single

/*
 *  Created by Hanantadk on 22/03/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 22/03/20.
 */

class DataSourceImpl(private val source: MovieAPI) : DataSource {

    override fun discoverMovie(page: Int): Single<List<MovieModel>> {
        return source.getMovies(apiKey = BuildConfig.API_KEY, page = page)
            .compose(ErrorNetworkHandler())
            .map { it.results }
    }
}
