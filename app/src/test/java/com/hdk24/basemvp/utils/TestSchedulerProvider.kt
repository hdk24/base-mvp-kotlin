package com.hdk24.basemvp.utils

import com.hdk24.basemvp.presentation.base.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.schedulers.TestScheduler

/*
 *  Created by Hanantadk on 22/03/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 22/03/20.
 */
class TestSchedulerProvider(private val mTestScheduler: TestScheduler) : SchedulerProvider {

    override fun ui(): Scheduler {
        return mTestScheduler
    }

    override fun computation(): Scheduler {
        return mTestScheduler
    }

    override fun io(): Scheduler {
        return mTestScheduler
    }
}
