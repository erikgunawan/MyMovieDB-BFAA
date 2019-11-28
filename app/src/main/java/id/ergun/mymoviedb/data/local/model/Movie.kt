package id.ergun.mymoviedb.data.local.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * Created by erikgunawan on 24/11/19.
 */
class Movie(
    val adult: Boolean = false,
    val backdropPath: String = "",
  val genreIds: MutableList<Int> = mutableListOf(),
    val id: Int? = null,
    val originalLanguage: String = "",
    val originalTitle: String = "",
  @StringRes val overview: Int,
    val popularity: Double = 0.0,
    val posterPath: String = "",
    val releaseDate: String = "",
  @StringRes val title: Int,
    val video: Boolean = false,
    val voteAverage: Int = 0,
    val voteCount: Int = 0,
    @DrawableRes val image: Int
)