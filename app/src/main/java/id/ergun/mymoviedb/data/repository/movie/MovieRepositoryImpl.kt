package id.ergun.mymoviedb.data.repository.movie

import id.ergun.mymoviedb.data.dataSource.movie.MovieDataSource
import id.ergun.mymoviedb.data.local.dao.MovieDao
import id.ergun.mymoviedb.data.mapper.MovieMapper
import id.ergun.mymoviedb.data.model.Movie
import id.ergun.mymoviedb.data.remote.model.MovieResponse
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by erikgunawan on 24/11/19.
 */
class MovieRepositoryImpl(
    private val remoteData: MovieDataSource,
    private val localData: MovieDao
) : MovieRepository {

    override fun getMovies(page: Int, gte: String?, lte: String?): Observable<MovieResponse> {
        return remoteData.getMovies(page = page, lte = lte, gte = gte)
    }

    override fun getMovieDetail(id: String): Observable<MovieResponse.Result> {
        return remoteData.getMovieDetail(
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

    override fun searchMovie(keyword: String): Observable<MovieResponse> {
        return remoteData.searchMovie(keyword)
    }
}