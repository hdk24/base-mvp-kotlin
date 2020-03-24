package com.hdk24.basemvp.presentation.base

/*
 *  Created by Hanantadk on 22/03/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 22/03/20.
 */
interface MVPView {

    fun initViews()

    fun showLoading(isShow: Boolean)

    fun showMessage(msg: String)
}
