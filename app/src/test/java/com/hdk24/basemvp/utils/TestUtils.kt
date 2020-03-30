package com.hdk24.basemvp.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hdk24.basemvp.data.remote.model.MovieResponse
import java.io.FileReader
import java.nio.file.Paths

/*
 *  Created by Hanantadk on 24/03/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 24/03/20.
 */
object TestUtils {
    fun readOrders(fileName: String): MovieResponse {
        return Gson().fromJson<MovieResponse>(
            FileReader(Paths.get(TestUtils::class.java.getResource(fileName).toURI()).toFile()),
            object : TypeToken<MovieResponse>() {}.type
        )
    }

    fun readJson(fileName: String): String {
        return FileReader(Paths.get(TestUtils::class.java.getResource(fileName).toURI()).toFile()).readText()
    }
}
