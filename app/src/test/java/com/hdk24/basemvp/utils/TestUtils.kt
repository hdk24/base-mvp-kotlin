package com.hdk24.basemvp.utils

import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader

/*
 *  Created by Hanantadk on 24/03/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 24/03/20.
 */
object TestUtils {
    fun getResponseJson(): String {
        val assetPath = "../app/src/main/assets/"
        val br =
            BufferedReader(InputStreamReader(FileInputStream(assetPath + "response_success.json")))
        val sb = StringBuilder()
        var line = br.readLine()
        while (line != null) {
            sb.append(line)
            line = br.readLine()
        }
        return sb.toString()
    }
}
