package com.hdk24.basemvp.presentation.presenter

import com.hdk24.basemvp.domain.exception.Failure
import com.hdk24.basemvp.domain.repository.MovieRepository
import com.hdk24.basemvp.presentation.base.BasePresenter
import com.hdk24.basemvp.presentation.base.SchedulerProvider
import com.hdk24.basemvp.presentation.exception.NetworkState
import com.hdk24.basemvp.presentation.view.MovieView

/*
 *  Created by Hanantadk on 22/03/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 22/03/20.
 */
class MoviePresenter(
    private val repository: MovieRepository,
    private val scheduler: SchedulerProvider
) : BasePresenter<MovieView.View>(), MovieView.Presenter {

    /**
     * get data post from repository
     * and handle lifecycle data to activity/ fragment
     * if (page == 1) load data from local first and update data from server
     * @param page pagination
     */
    override fun fetchMovie(page: Int) {
        disposeLast()
        mView.showLoading(true)
        lastDisposable = repository.getCacheMovie()
            .flatMap {
                if (page == 1 && it.isNotEmpty()) {
                    mView.setData(it)
                    mView.showLoading(false)
                }
                repository.fetchMovie(page)
            }
            .subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())
            .subscribe({
                mView.setData(it)
                mView.showLoading(false)
            }, {
                if (it is Failure) {
                    val error = NetworkState.error(it as Failure)
                    mView.showMessage(error.failure?.msg ?: "Error")
                }
                mView.showLoading(false)
            })

        compositeDisposable.let { compositeDisposable.add(it) }
    }
}
