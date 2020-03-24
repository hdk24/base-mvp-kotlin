package com.hdk24.basemvp.data.local.dao

import androidx.room.*
import com.hdk24.basemvp.data.local.entity.MovieEntity
import io.reactivex.Single

/*
 *  Created by Hanantadk on 22/03/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 22/03/20.
 */

@Dao
interface MovieDao {

    @Query("SELECT * FROM MovieTable")
    fun getAll(): Single<List<MovieEntity>>

    @Query("SELECT * FROM MovieTable WHERE title LIKE '%' || :keyword || '%' ORDER BY id DESC")
    fun getByKeyword(keyword: String): Single<List<MovieEntity>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(data: List<MovieEntity>)

    @Query("DELETE FROM MovieTable WHERE id == :data")
    fun delete(data: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: List<MovieEntity>)

    @Query("DELETE FROM MovieTable")
    fun truncate()
}
