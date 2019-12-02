package id.ergun.mymoviedb.data.mapper

import id.ergun.mymoviedb.data.Const
import id.ergun.mymoviedb.data.model.Tv
import id.ergun.mymoviedb.data.remote.model.TvResponse

class TvShowMapper {

    fun fromRemote(response: TvResponse): MutableList<Tv> {
        val tvShows = mutableListOf<Tv>()
        response.results?.forEach {
            tvShows.add(fromRemote(it))
        }

        return tvShows
    }

    fun fromRemote(tv: TvResponse.Result): Tv {
        return Tv(
            id = tv.id ?: 0,
            name = tv.name ?: "",
            overview = tv.overview ?: "",
            posterPath = Const.IMAGE_URL + tv.posterPath
        )
    }
}