package com.hdk24.basemvp.data.config

import android.content.Context
import java.io.IOException

/*
 *  Created by Hanantadk on 22/03/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 22/03/20.
 */

/**
 * Read File Json from assets
 * just put file with format .json in folder assets/json/
 * and set value jsonName with your json filename
 *
 * @return String json
 */
fun loadJSONFromAsset(context: Context, jsonName: String): String? {

    return try {
        val stream = context.assets.open("json/$jsonName.json")
        val size = stream.available()
        val buffer = ByteArray(size)
        stream.read(buffer)
        stream.close()
        String(buffer, Charsets.UTF_8)
    } catch (ex: IOException) {
        ex.printStackTrace()
        null
    }
}
