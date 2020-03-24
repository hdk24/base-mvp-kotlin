package com.hdk24.basemvp.utils

import android.app.ActivityManager

/*
 *  Created by Hanantadk on 22/03/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 22/03/20.
 */
object AppUtils {

    /**
     * check if app is minimized or not
     * @return boolean
     */
    fun isAppOnForeground(): Boolean {
        val myProcess = ActivityManager.RunningAppProcessInfo()
        ActivityManager.getMyMemoryState(myProcess)
        return myProcess.importance != ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND
    }
}
