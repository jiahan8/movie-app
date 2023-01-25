package com.jiahan.fave.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

/**
 * Movie Table in Room Database
 */
@Parcelize
@Entity(tableName = "movies")
data class Movie constructor(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val movie_id: Int,
    val poster_path: String?,
    val title: String,
    val popularity: Float,
) : Parcelable