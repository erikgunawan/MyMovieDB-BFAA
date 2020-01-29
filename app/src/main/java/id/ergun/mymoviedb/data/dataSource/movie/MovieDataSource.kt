package id.ergun.mymoviedb.data.dataSource.movie

import id.ergun.mymoviedb.data.remote.model.MovieResponse
import io.reactivex.Observable

/**
 * Created by alfacart on 31/12/19.
 */
interface MovieDataSource {
    fun getMovies(page: Int, gte: String?, lte: String?): Observable<MovieResponse>
    fun getMovieDetail(id: String): Observable<MovieResponse.Result>
    fun searchMovie(keyword: String): Observable<MovieResponse>
}