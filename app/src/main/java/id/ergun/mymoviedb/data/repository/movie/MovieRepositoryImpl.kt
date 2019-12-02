package id.ergun.mymoviedb.data.repository.movie

import android.content.Context
import id.ergun.mymoviedb.R
import id.ergun.mymoviedb.data.remote.AppService
import id.ergun.mymoviedb.data.remote.model.MovieResponse
import io.reactivex.Observable

/**
 * Created by erikgunawan on 24/11/19.
 */
class MovieRepositoryImpl(
    private val context: Context,
    private val remoteData: AppService
) : MovieRepository {

    override fun getMovies(): Observable<MovieResponse> {
        return remoteData.getMovies(language = context.getString(R.string.language_param))
    }

    override fun getMovieDetail(id: String): Observable<MovieResponse.Result> {
        return remoteData.getMovieDetail(
            language = context.getString(R.string.language_param),
            id = id
        )
    }
}