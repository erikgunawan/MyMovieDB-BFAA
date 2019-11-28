package id.ergun.mymoviedb.data.repository.movie

import id.ergun.mymoviedb.data.model.Movie
import io.reactivex.Observable

/**
 * Created by erikgunawan on 24/11/19.
 */
interface MovieRepository {
    fun getMovies(): Observable<MutableList<Movie>>
}