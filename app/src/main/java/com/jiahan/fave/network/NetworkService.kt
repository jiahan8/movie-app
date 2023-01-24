package com.jiahan.fave.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkService {

    @GET("discover/movie/")
    suspend fun getMovieList(
        @Query("api_key") apiKey : String,
        @Query("primary_release_date.lte") primaryReleaseDataLte : String,
        @Query("primary_release_date.gte") primaryReleaseDataGte : String,
        @Query("sort_by") sortBy : String,
        @Query( "page") page : Int,
    ): NetworkMovies

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId : Int,
        @Query("api_key") apiKey : String,
    ): NetworkMovieDetail

    /**
     * Main entry point for network access.
     */
    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/3/"
        /**
         * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
         * full Kotlin compatibility.
         */
        private val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        fun create() : NetworkService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
                .create(NetworkService::class.java)
        }
    }

}
