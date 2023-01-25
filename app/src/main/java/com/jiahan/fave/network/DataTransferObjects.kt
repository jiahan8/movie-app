package com.jiahan.fave.network

import com.jiahan.fave.database.Movie
import com.jiahan.fave.domain.MovieDetail
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkMovies(
    val results: List<NetworkMovie>,
    val total_pages: Int
)

@JsonClass(generateAdapter = true)
data class NetworkMovie(
    val id: Int,
    val poster_path: String?,
    val title: String,
    val popularity: Float,
)

@JsonClass(generateAdapter = true)
data class NetworkMovieDetail(
    val id: Int,
    val poster_path: String?,
    val title: String,
    val popularity: Float,
    val overview: String,
    val genres: List<Genre>,
    val original_language: String,
    val runtime: Int,
)

@JsonClass(generateAdapter = true)
data class Genre(
    val id: Int,
    val name: String,
)

fun NetworkMovies.asDatabaseModel(): Array<Movie> {
    return results.map {
        Movie(
            0,
            movie_id = it.id,
            poster_path = it.poster_path,
            title = it.title,
            popularity = it.popularity,
        )
    }.toTypedArray()
}

fun NetworkMovieDetail.asDomainModel(): MovieDetail {
    return MovieDetail(
        movie_id = id,
        poster_path = poster_path,
        title = title,
        popularity = popularity,
        overview = overview,
        genres = genres,
        original_language = original_language,
        runtime = runtime,
    )
}