package id.ergun.mymoviedb.data.repository.tvShow

import id.ergun.mymoviedb.data.remote.model.TvResponse
import io.reactivex.Observable

/**
 * Created by erikgunawan on 27/11/19.
 */
interface TvShowRepository {
    fun getTvShows(): Observable<TvResponse>
}