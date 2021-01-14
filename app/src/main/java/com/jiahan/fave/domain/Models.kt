package com.jiahan.fave.domain

import android.os.Parcelable
import com.jiahan.fave.network.Genre
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val movie_id: Int,
    val poster_path: String,
    val title: String,
    val popularity: Float,
) : Parcelable

data class MovieDetail(
    val movie_id: Int,
    val poster_path: String,
    val title: String,
    val popularity: Float,
    val overview: String?,
    val genres: List<Genre>?,
    val original_language: String?,
    val runtime: Int?,
)