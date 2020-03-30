package com.hdk24.basemvp

import android.content.Context
import androidx.room.Room
import com.hdk24.basemvp.data.local.AppDatabase
import com.hdk24.basemvp.data.remote.api.MovieAPI
import com.hdk24.basemvp.data.remote.datasource.DataSource
import com.hdk24.basemvp.data.remote.datasource.DataSourceImpl
import com.hdk24.basemvp.data.remote.model.MovieResponse
import com.hdk24.basemvp.data.repository.MovieRepositoryImpl
import com.hdk24.basemvp.domain.repository.MovieRepository
import com.hdk24.basemvp.presentation.presenter.MoviePresenter
import com.hdk24.basemvp.presentation.view.MovieView
import com.hdk24.basemvp.utils.TestSchedulerProvider
import com.hdk24.basemvp.utils.TestUtils
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.TestScheduler
import junit.framework.Assert.assertEquals
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/*
 *  Created by Hanantadk on 22/03/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 22/03/20.
 */
@RunWith(MockitoJUnitRunner::class)
class MoviePresenterTest {
    @Mock
    lateinit var view: MovieView.View
    @Mock
    lateinit var presenter: MovieView.Presenter
    @Mock
    lateinit var dataSource: DataSource
    @Mock
    lateinit var context: Context

    private lateinit var repository: MovieRepository
    private lateinit var movieAPI: MovieAPI
    private lateinit var server: MockWebServer
    private lateinit var database: AppDatabase
    private lateinit var compositeDisposable: CompositeDisposable
    private lateinit var testSchedulerProvide: TestSchedulerProvider
    private lateinit var testScheduler: TestScheduler

    @Before
    @Throws
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        movieAPI = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BuildConfig.API_URL)
            .client(OkHttpClient())
            .build().create(MovieAPI::class.java)

        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()

        testScheduler = TestScheduler()
        dataSource = DataSourceImpl(movieAPI)

        compositeDisposable = CompositeDisposable()
        testSchedulerProvide =
            TestSchedulerProvider(testScheduler)
        repository = MovieRepositoryImpl(dataSource, database)

        presenter = MoviePresenter(repository, testSchedulerProvide)
        presenter.onAttach(view)

        server = MockWebServer()
        server.start()
    }

    @Test
    fun fetchMovieSuccess() {
        val testObserver = TestObserver<MovieResponse>()
        val mockResponse = MockResponse().setBody(TestUtils.readJson("/response.json"))

        server.enqueue(mockResponse)
        server.url(BuildConfig.API_URL)

        presenter.fetchMovie(1)
        testObserver.awaitTerminalEvent(2, TimeUnit.SECONDS)
        testObserver.assertNoErrors()

        testScheduler.triggerActions()

        val serverRequest = server.takeRequest()
        assertEquals(BuildConfig.API_URL, serverRequest.path)
        assertEquals(TestUtils.readJson("/response.json"), serverRequest.body.readUtf8())
    }

    @Test
    fun checkReadFile() {
        val jsonFile = TestUtils.readJson("/response.json")
        print(jsonFile)
        assert(jsonFile.isNotEmpty())
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        presenter.onDetach()
        database.close()
        server.shutdown()
    }

}
