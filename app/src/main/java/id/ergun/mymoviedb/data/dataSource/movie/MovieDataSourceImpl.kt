package id.ergun.mymoviedb.data.dataSource.movie

import android.content.Context
import id.ergun.mymoviedb.R
import id.ergun.mymoviedb.data.remote.AppService
import id.ergun.mymoviedb.data.remote.model.MovieResponse
import io.reactivex.Observable

/**
 * Created by alfacart on 31/12/19.
 */

class MovieDataSourceImpl(
    private val context: Context,
    private val remoteData: AppService
) : MovieDataSource {

    override fun getMovies(page: Int, gte: String?, lte: String?): Observable<MovieResponse> {
        return remoteData.getMovies(
            language = context.getString(R.string.language_param),
            page = page,
            lte = lte,
            gte = gte
        )
    }

    override fun getMovieDetail(id: String): Observable<MovieResponse.Result> {
        return remoteData.getMovieDetail(
            language = context.getString(R.string.language_param),
            id = id
        )
    }
}