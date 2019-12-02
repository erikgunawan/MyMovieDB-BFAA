package id.ergun.mymoviedb.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Tv(
    val backdropPath: String = "",
//    val createdBy: MutableList<CreatedBy> = mutableListOf(),
    val episodeRunTime: List<Int> = mutableListOf(),
    val firstAirDate: String = "",
//    val genres: List<Genre> = mutableListOf(),
    val homepage: String = "",
    val id: Int = 0,
    val inProduction: Boolean = false,
    val languages: List<String> = mutableListOf(),
    val lastAirDate: String = "",
//    val lastEpisodeToAir: LastEpisodeToAir?,
    val name: String = "",
//    val networks: List<Network> = mutableListOf(),
//    val nextEpisodeToAir: NextEpisodeToAir?,
    val numberOfEpisodes: Int = 0,
    val numberOfSeasons: Int = 0,
    val originCountry: MutableList<String> = mutableListOf(),
    val originalLanguage: String = "",
    val originalName: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val posterPath: String = "",
//    val productionCompanies: MutableList<ProductionCompany> = mutableListOf(),
//    val seasons: MutableList<Season> = mutableListOf(),
    val status: String = "",
    val type: String = "",
    val voteAverage: Double = 0.0,
    val voteCount: Int = 0
) : Parcelable