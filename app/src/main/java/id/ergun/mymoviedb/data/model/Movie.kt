package id.ergun.mymoviedb.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by erikgunawan on 24/11/19.
 */
@Parcelize
class Movie(
    val adult: Boolean = false,
    val backdropPath: String = "",
    val genreIds: List<Int>? = mutableListOf(),
    val id: Int? = null,
    val originalLanguage: String = "",
    val originalTitle: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val posterPath: String = "",
    val releaseDate: String = "",
    val title: String = "",
    val video: Boolean = false,
    val voteAverage: Int = 0,
    val voteCount: Int = 0
) : Parcelable