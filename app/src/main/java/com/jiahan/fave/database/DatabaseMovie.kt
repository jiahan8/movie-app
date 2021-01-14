package com.jiahan.fave.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jiahan.fave.domain.Movie
import kotlinx.parcelize.Parcelize

/**
 * Movie Table in Room Database
 */
@Parcelize
@Entity
data class DatabaseMovie constructor(
       @PrimaryKey(autoGenerate = true)
       val id: Int,
       val movie_id: Int,
       val poster_path: String,
       val title: String,
       val popularity: Float,
) : Parcelable

// mapping movie object for domain use
fun List<DatabaseMovie>.asDomainModel(): List<Movie> {
       return map{
              Movie(
                     movie_id = it.movie_id,
                     poster_path = it.poster_path,
                     title = it.title,
                     popularity = it.popularity,
              )
       }
}