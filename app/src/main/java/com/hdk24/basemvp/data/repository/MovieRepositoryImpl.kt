package com.hdk24.basemvp.data.repository

import com.hdk24.basemvp.data.local.AppDatabase
import com.hdk24.basemvp.data.mapper.MovieMapper
import com.hdk24.basemvp.data.mapper.MovieTableMapper
import com.hdk24.basemvp.data.remote.datasource.DataSource
import com.hdk24.basemvp.domain.repository.MovieRepository
import com.hdk24.basemvp.presentation.model.Movie
import io.reactivex.Single

/*
 *  Created by Hanantadk on 22/03/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 22/03/20.
 *
 * reference: https://medium.com/mobiwise-blog/load-cache-before-api-call-observable-concat-f527f267656
 */

class MovieRepositoryImpl(
    private val apiSource: DataSource,
    private val dbSource: AppDatabase
) : MovieRepository {

    override fun fetchMovie(page: Int): Single<List<Movie>> {
        return apiSource.discoverMovie(page)
            .map { MovieMapper.transformTo(it) }
            .doOnSuccess {
                if (page == 1) dbSource.movieDao().insert(MovieTableMapper.transformTo(it))
            }
    }

    override fun getCacheMovie(): Single<List<Movie>> {
        return dbSource.movieDao().getAll()
            .map { MovieTableMapper.transformFrom(it) }
    }
}
