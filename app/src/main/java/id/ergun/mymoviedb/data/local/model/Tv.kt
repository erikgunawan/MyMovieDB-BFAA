package id.ergun.mymoviedb.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by erikgunawan on 24/11/19.
 */
@Entity(tableName = "tv")
data class Tv(
    @PrimaryKey
    val id: Int,
    val name: String = "",
    val overview: String = "",
    val posterPath: String = ""
//    val backdropPath: String = "",
//    val createdBy: MutableList<CreatedBy> = mutableListOf(),
//    val episodeRunTime: List<Int> = mutableListOf(),
//    val firstAirDate: String = "",
//    val genres: List<Genre> = mutableListOf(),
//    val homepage: String = "",
//    val inProduction: Boolean = false,
//    val languages: List<String> = mutableListOf(),
//    val lastAirDate: String = "",
//    val lastEpisodeToAir: LastEpisodeToAir?,
//    val networks: List<Network> = mutableListOf(),
//    val nextEpisodeToAir: NextEpisodeToAir?,
//    val numberOfEpisodes: Int = 0,
//    val numberOfSeasons: Int = 0,
//    val originCountry: MutableList<String> = mutableListOf(),
//    val originalLanguage: String = "",
//    val originalName: String = "",
//    val popularity: Double = 0.0,
//    val productionCompanies: MutableList<ProductionCompany> = mutableListOf(),
//    val seasons: MutableList<Season> = mutableListOf(),
//    val status: String = "",
//    val type: String = "",
//    val voteAverage: Double = 0.0,
//    val voteCount: Int = 0
)