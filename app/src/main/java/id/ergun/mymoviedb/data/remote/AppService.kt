package id.ergun.mymoviedb.data.remote

import id.ergun.mymoviedb.BuildConfig.API_KEY_VALUE
import id.ergun.mymoviedb.data.Const.API_KEY
import id.ergun.mymoviedb.data.Const.LANGUAGE
import id.ergun.mymoviedb.data.Const.LANGUAGE_EN_US
import id.ergun.mymoviedb.data.Const.QUERY
import id.ergun.mymoviedb.data.remote.model.MovieResponse
import id.ergun.mymoviedb.data.remote.model.TvResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by erikgunawan on 28/11/19.
 */
interface AppService {

    @GET("3/discover/movie")
    fun getMovies(
        @Query(API_KEY) apiKey: String = API_KEY_VALUE, @Query(
            LANGUAGE
        ) language: String = LANGUAGE_EN_US, @Query("page") page: Int?, @Query("primary_release_date.gte") gte: String? = null,
        @Query("primary_release_date.lte") lte: String? = null
    ): Observable<MovieResponse>

    @GET("3/movie/{id}")
    fun getMovieDetail(
        @Path("id") id: String,
        @Query(API_KEY) apiKey: String = API_KEY_VALUE, @Query(
            LANGUAGE
        ) language: String = LANGUAGE_EN_US
    ): Observable<MovieResponse.Result>


    @GET("3/search/movie")
    fun searchMovie(
        @Query(API_KEY) apiKey: String = API_KEY_VALUE, @Query(
            LANGUAGE
        ) language: String = LANGUAGE_EN_US, @Query("page") page: Int, @Query(QUERY) query: String
    ): Observable<MovieResponse>

    @GET("3/discover/tv")
    fun getTvShows(
        @Query(API_KEY) apiKey: String = API_KEY_VALUE, @Query(
            LANGUAGE
        ) language: String = LANGUAGE_EN_US, @Query("page") page: Int = 1
    ): Observable<TvResponse>

    @GET("3/tv/{id}")
    fun getTvDetail(
        @Path("id") id: String,
        @Query(API_KEY) apiKey: String = API_KEY_VALUE, @Query(
            LANGUAGE
        ) language: String = LANGUAGE_EN_US
    ): Observable<TvResponse.Result>

    @GET("3/search/tv")
    fun searchTvShow(
        @Query(API_KEY) apiKey: String = API_KEY_VALUE, @Query(
            LANGUAGE
        ) language: String = LANGUAGE_EN_US, @Query(QUERY) query: String, @Query("page") page: Int
    ): Observable<TvResponse>

}