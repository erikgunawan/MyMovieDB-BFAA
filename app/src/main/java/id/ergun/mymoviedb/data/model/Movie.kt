package id.ergun.mymoviedb.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by erikgunawan on 24/11/19.
 */
@Parcelize
class Movie(
    val id: Int? = null,
    val overview: String = "",
    val posterPath: String = "",
    val title: String = ""
//    val adult: Boolean = false,
//    val backdropPath: String = "",
//    val genreIds: List<Int>? = mutableListOf(),
//    val originalLanguage: String = "",
//    val originalTitle: String = "",
//    val popularity: Double = 0.0,
//    val releaseDate: String = "",
//    val video: Boolean = false,
//    val voteAverage: Int = 0,
//    val voteCount: Int = 0
) : Parcelable