package com.hdk24.basemvp.presentation.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/*
 *  Created by Hanantadk on 22/03/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 22/03/20.
 */
abstract class BasePresenter<V : MVPView> : MVPPresenter<V>, LifecycleObserver {

    protected var lastDisposable: Disposable? = null

    val compositeDisposable = CompositeDisposable()

    protected lateinit var mView: V

    /**
     * dispose last disposable to handle multiple request
     */
    fun disposeLast() {
        lastDisposable?.let {
            if (!it.isDisposed) it.dispose()
        }
    }

    /**
     * attach presenter to activity or fragment
     * @param view
     */
    override fun onAttach(view: V) {
        this.mView = view
        mView.initViews()
        if (mView is LifecycleOwner) {
            (mView as LifecycleOwner).lifecycle.addObserver(this)
        }
    }

    /**
     * dispose and clear when activity on destroy
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    override fun onDetach() {
        compositeDisposable.dispose()
        compositeDisposable.clear()
    }
}
