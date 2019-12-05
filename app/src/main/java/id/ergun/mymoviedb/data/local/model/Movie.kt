package id.ergun.mymoviedb.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by erikgunawan on 24/11/19.
 */
@Entity(tableName = "movie")
data class Movie(

    @PrimaryKey
  val id: Int,
    val overview: String,
    val posterPath: String = "",
    val title: String
//  val originalTitle: String = "",
//  val adult: Boolean = false,
//  val backdropPath: String = "",
//  val genreIds: MutableList<Int> = mutableListOf(),
//  val originalLanguage: String = "",
//  val popularity: Double = 0.0,
//  val releaseDate: String = "",
//  val video: Boolean = false,
//  val voteAverage: Int = 0,
//  val voteCount: Int = 0
)