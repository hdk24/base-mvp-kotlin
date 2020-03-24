package com.hdk24.basemvp.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.hdk24.basemvp.BuildConfig
import com.hdk24.basemvp.data.remote.api.MovieAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/*
 *  Created by Hanantadk on 22/03/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 22/03/20.
 */

val networkModule = module {

    // inject Gson
    single<Gson> { GsonBuilder().excludeFieldsWithoutExposeAnnotation().create() }

    // inject okHttp
    single<OkHttpClient> {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .build()
    }

    // inject retrofit
    single<Retrofit> {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(get()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BuildConfig.API_URL)
            .client(get())
            .build()
    }

    // inject api service
    single<MovieAPI> {
        get<Retrofit>().create(MovieAPI::class.java)
    }
}
