package id.ergun.mymoviedb.data.dataSource.tvShow

import id.ergun.mymoviedb.data.remote.model.TvResponse
import io.reactivex.Observable

/**
 * Created by alfacart on 03/02/20.
 */
interface TvShowDataSource {
    fun getTvShows(page: Int): Observable<TvResponse>
    fun getTvDetail(id: String): Observable<TvResponse.Result>
    fun searchTvShow(page: Int, keyword: String): Observable<TvResponse>
}