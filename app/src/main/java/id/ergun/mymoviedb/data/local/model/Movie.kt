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
  val adult: Boolean = false,
  val backdropPath: String = "",
//  val genreIds: MutableList<Int> = mutableListOf(),
  val originalLanguage: String = "",
  val originalTitle: String = "",
  val overview: String,
  val popularity: Double = 0.0,
  val posterPath: String = "",
  val releaseDate: String = "",
  val title: String,
  val video: Boolean = false,
  val voteAverage: Int = 0,
  val voteCount: Int = 0
)