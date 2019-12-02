package id.ergun.mymoviedb.data.remote

import id.ergun.mymoviedb.data.Const.API_KEY
import id.ergun.mymoviedb.data.Const.API_KEY_VALUE
import id.ergun.mymoviedb.data.Const.LANGUAGE
import id.ergun.mymoviedb.data.Const.LANGUAGE_EN_US
import id.ergun.mymoviedb.data.remote.model.MovieResponse
import id.ergun.mymoviedb.data.remote.model.TvResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by erikgunawan on 28/11/19.
 */
interface AppService {

    @GET("3/discover/movie")
    fun getMovies(
        @Query(API_KEY) apiKey: String = API_KEY_VALUE, @Query(
            LANGUAGE
        ) language: String = LANGUAGE_EN_US
    ): Observable<MovieResponse>

    @GET("3/discover/tv")
    fun getTvShows(
        @Query(API_KEY) apiKey: String = API_KEY_VALUE, @Query(
            LANGUAGE
        ) language: String = LANGUAGE_EN_US
    ): Observable<TvResponse>

}