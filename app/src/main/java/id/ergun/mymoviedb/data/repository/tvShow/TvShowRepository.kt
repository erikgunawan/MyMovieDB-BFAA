package id.ergun.mymoviedb.data.repository.tvShow

import id.ergun.mymoviedb.data.model.Tv
import id.ergun.mymoviedb.data.remote.model.TvResponse
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by erikgunawan on 27/11/19.
 */
interface TvShowRepository {
    fun getTvShows(page: Int): Observable<TvResponse>
    fun getTvDetail(id: String): Observable<TvResponse.Result>
    fun getFavoriteTvShows(): Observable<MutableList<Tv>>
    fun getFavoriteTvShow(id: String): Observable<Tv>
    fun addToFavorite(tv: Tv): Single<Unit>
    fun removeFromFavorite(tv: Tv): Single<Unit>
    fun getSearchTvShow(page: Int, keyword: String): Observable<TvResponse>
}