package id.ergun.mymoviedb.data.repository.movie

import id.ergun.mymoviedb.data.model.Movie
import id.ergun.mymoviedb.data.remote.model.MovieResponse
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by erikgunawan on 24/11/19.
 */
interface MovieRepository {
    fun getMovies(): Observable<MovieResponse>
    fun getMovieDetail(id: String): Observable<MovieResponse.Result>
    fun getFavoriteMovies(): Observable<MutableList<Movie>>
    fun getFavoriteMovie(id: String): Observable<Movie>
    fun addToFavorite(movie: Movie): Single<Unit>
    fun removeFromFavorite(movie: Movie): Single<Unit>
}