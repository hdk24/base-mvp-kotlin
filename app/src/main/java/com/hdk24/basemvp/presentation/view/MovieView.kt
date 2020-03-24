package com.hdk24.basemvp.presentation.view

import com.hdk24.basemvp.presentation.base.MVPPresenter
import com.hdk24.basemvp.presentation.base.MVPView
import com.hdk24.basemvp.presentation.model.Movie

/*
 *  Created by Hanantadk on 22/03/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 22/03/20.
 */
class MovieView {

    /**
     * list interface view
     * add what view needed to communicate with presenter
     */
    interface View : MVPView {
        fun setData(list: List<Movie>)
    }

    /**
     * list presenter
     * function to manage/ get all data from repository
     */
    interface Presenter : MVPPresenter<View> {
        fun fetchMovie(page: Int)
    }
}
