package id.ergun.mymoviedb.data.repository.tvShow

import id.ergun.mymoviedb.data.model.Tv
import io.reactivex.Observable

/**
 * Created by alfacart on 27/11/19.
 */
interface TvShowRepository {
  fun getTvShows(): Observable<MutableList<Tv>>
}