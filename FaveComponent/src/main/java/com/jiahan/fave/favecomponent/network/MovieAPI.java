package com.jiahan.fave.favecomponent.network;

import com.jiahan.fave.favecomponent.entity.MovieDetail;
import com.jiahan.fave.favecomponent.network.pojo.response.MoviesResponse;
//import com.squareup.moshi.Moshi;
//import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory;

import io.reactivex.rxjava3.core.Observable;
//import retrofit2.Retrofit;
//import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
//import retrofit2.converter.moshi.MoshiConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieAPI {

     @GET("discover/movie/")
     Observable<MoviesResponse> getMovieList(
             @Query("api_key") String apiKey,
             @Query("primary_release_date.lte") String primaryReleaseDataLte,
             @Query("primary_release_date.gte") String primaryReleaseDataGte,
             @Query("sort_by") String sortBy,
             @Query( "page") int page);

     @GET("movie/{movie_id}")
     Observable<MovieDetail> getMovieDetail(
             @Path("movie_id") int movieId,
             @Query("api_key") String apiKey);


}
