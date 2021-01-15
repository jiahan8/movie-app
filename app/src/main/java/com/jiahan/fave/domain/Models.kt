package com.jiahan.fave.domain

import com.jiahan.fave.network.Genre

data class MovieDetail(
    val movie_id: Int,
    val poster_path: String?,
    val title: String,
    val popularity: Float,
    val overview: String?,
    val genres: List<Genre>?,
    val original_language: String?,
    val runtime: Int?,
)