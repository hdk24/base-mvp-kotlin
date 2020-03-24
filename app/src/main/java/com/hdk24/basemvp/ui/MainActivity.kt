package com.hdk24.basemvp.ui

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.hdk24.basemvp.R
import com.hdk24.basemvp.presentation.base.BaseActivity
import com.hdk24.basemvp.presentation.model.Movie
import com.hdk24.basemvp.presentation.presenter.MoviePresenter
import com.hdk24.basemvp.presentation.view.MovieView
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

/*
 *  Created by Hanantadk on 22/03/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 22/03/20.
 */
class MainActivity : BaseActivity(), MovieView.View {

    // inject presenter
    private val mPresenter: MoviePresenter by inject()

    /**
     * getLayoutId
     * set layout for this activity
     */
    override fun getLayoutId(): Int = R.layout.activity_main

    /**
     * onViewReady
     * init all action when view is ready here
     */
    override fun onViewReady(savedInstance: Bundle?) {
        mPresenter.onAttach(this)
        mPresenter.fetchMovie(1)
    }

    /**
     * initViews
     * init, prepare, and config all views and that action for first time
     */
    override fun initViews() {
        // setup view here
        message_view.text = "initial views ..."
    }

    /**
     * showLoading
     * show or hide loading view if needed
     * @param isShow state loading
     */
    override fun showLoading(isShow: Boolean) {
        // handle loading state
        if (isShow) message_view.text = "show loading ..."
    }

    /**
     * showMessage
     * set message information
     * @param msg text message
     */
    override fun showMessage(msg: String) {
        Snackbar.make(container_main, msg, Snackbar.LENGTH_LONG).show()
    }

    /**
     *  set data to view
     *  @param list movie data
     */
    override fun setData(list: List<Movie>) {
        message_view.text = "Movie size ${list.size}"
    }

    /**
     * onDestroy
     * stop presenter when activity onDestroy
     */
    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDetach()
    }
}
