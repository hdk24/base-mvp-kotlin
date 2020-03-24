package com.hdk24.basemvp.presentation.base

/*
 *  Created by Hanantadk on 22/03/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 22/03/20.
 */
interface MVPPresenter<V : MVPView> {

    fun onAttach(view: V)

    fun onDetach()
}
