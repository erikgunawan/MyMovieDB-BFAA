package id.ergun.mymoviedb.data.repository.movie

import android.content.Context
import id.ergun.mymoviedb.R
import id.ergun.mymoviedb.data.local.dao.MovieDao
import id.ergun.mymoviedb.data.mapper.MovieMapper
import id.ergun.mymoviedb.data.model.Movie
import id.ergun.mymoviedb.data.remote.AppService
import id.ergun.mymoviedb.data.remote.model.MovieResponse
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by erikgunawan on 24/11/19.
 */
class MovieRepositoryImpl(
    private val context: Context,
    private val remoteData: AppService,
    private val localData: MovieDao
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

    override fun getFavoriteMovies(): Observable<MutableList<Movie>> {
        return Observable.fromCallable {
            localData.getMovies().map {
                MovieMapper().fromLocal(it)
            }.toMutableList()
        }
    }

    override fun getFavoriteMovie(id: String): Observable<Movie> {
        return Observable.fromCallable { localData.getMovie(id) }
            .map { MovieMapper().fromLocal(it) }
    }

    override fun addToFavorite(movie: Movie): Single<Unit> {
        return Single.fromCallable {
            localData.insertMovie(MovieMapper().toLocal(movie))
        }
    }

    override fun removeFromFavorite(movie: Movie): Single<Unit> {
        return Single.fromCallable {
            localData.deleteMovie(MovieMapper().toLocal(movie))
        }
    }
}