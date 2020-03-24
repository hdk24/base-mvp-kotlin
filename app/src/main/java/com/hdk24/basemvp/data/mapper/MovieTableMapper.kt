package com.hdk24.basemvp.data.mapper

import com.hdk24.basemvp.data.local.entity.MovieEntity
import com.hdk24.basemvp.presentation.model.Movie

/*
 *  Created by Hanantadk on 22/03/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 22/03/20.
 */

object MovieTableMapper : Mapper<List<Movie>, List<MovieEntity>>() {

    /**
     * transform database to model view
     * @param source from database
     * @return model view
     */
    override fun transformFrom(source: List<MovieEntity>): List<Movie> {
        return source.map {
            Movie(
                id = it.id,
                voteCount = it.voteCount,
                voteAverage = it.voteAverage,
                title = it.title,
                popularity = it.popularity,
                posterPath = it.posterPath,
                originalLanguage = it.originalLanguage,
                backdropPath = it.backdropPath,
                overview = it.overview
            )
        }
    }

    /**
     * transform model from model view to database
     * @param source model vie
     * @return list data from database
     */
    override fun transformTo(source: List<Movie>): List<MovieEntity> {
        return source.map {
            MovieEntity(
                id = it.id,
                voteCount = it.voteCount,
                voteAverage = it.voteAverage,
                title = it.title,
                popularity = it.popularity,
                posterPath = it.posterPath,
                originalLanguage = it.originalLanguage,
                backdropPath = it.backdropPath,
                overview = it.overview
            )
        }
    }
}
