package id.ergun.mymoviedb.data.repository.movie

import id.ergun.mymoviedb.data.remote.model.MovieResponse
import io.reactivex.Observable

/**
 * Created by erikgunawan on 24/11/19.
 */
interface MovieRepository {
    fun getMovies(): Observable<MovieResponse>
    fun getMovieDetail(id: String): Observable<MovieResponse.Result>
}