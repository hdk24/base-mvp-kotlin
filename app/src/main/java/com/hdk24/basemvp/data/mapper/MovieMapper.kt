package com.hdk24.basemvp.data.mapper

import com.hdk24.basemvp.data.remote.model.MovieModel
import com.hdk24.basemvp.presentation.model.Movie

/*
 *  Created by Hanantadk on 22/03/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 22/03/20.
 */

object MovieMapper : Mapper<List<MovieModel>, List<Movie>>() {

    /**
     * map model to api response
     * @param source list of model
     * @return response api
     */
    override fun transformFrom(source: List<Movie>): List<MovieModel> {
        // To change body of created functions use File | Settings | File Templates.
        TODO("not implemented")
    }

    /**
     * map api response to model view
     * @param source api response
     * @return list model used in view
     */
    override fun transformTo(source: List<MovieModel>): List<Movie> {
        return source.map {
            Movie(
                id = it.id ?: 0,
                voteCount = it.voteCount ?: 0,
                voteAverage = it.voteAverage ?: 0.0,
                title = it.title ?: "",
                popularity = it.popularity ?: 0.0,
                posterPath = it.posterPath ?: "",
                originalLanguage = it.originalLanguage ?: "",
                backdropPath = it.backdropPath ?: "",
                overview = it.overview ?: ""
            )
        }
    }
}
