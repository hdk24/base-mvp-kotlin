package com.hdk24.basemvp.data.mapper

/*
 *  Created by Hanantadk on 22/03/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 22/03/20.
 */

abstract class Mapper<T, K> {

    abstract fun transformFrom(source: K): T

    abstract fun transformTo(source: T): K

    fun transformFromList(source: List<K>): List<T> {
        return source.map { src -> transformFrom(src) }
    }

    fun transformToList(source: List<T>): List<K> {
        return source.map { src -> transformTo(src) }
    }
}
