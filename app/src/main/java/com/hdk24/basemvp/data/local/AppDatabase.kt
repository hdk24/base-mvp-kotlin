package com.hdk24.basemvp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hdk24.basemvp.data.local.AppDatabase.Companion.DB_VERSION
import com.hdk24.basemvp.data.local.dao.MovieDao
import com.hdk24.basemvp.data.local.entity.MovieEntity

/*
 *  Created by Hanantadk on 22/03/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 22/03/20.
 */

@Database(entities = [MovieEntity::class], version = DB_VERSION, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val DB_NAME = "basemvp.db"
        const val DB_VERSION = 1
    }

    abstract fun movieDao(): MovieDao
}
