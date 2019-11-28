package id.ergun.mymoviedb.data.repository.tvShow

import id.ergun.mymoviedb.data.local.db.TvShowData
import id.ergun.mymoviedb.data.mapper.TvShowMapper
import id.ergun.mymoviedb.data.model.Tv
import io.reactivex.Observable

/**
 * Created by alfacart on 27/11/19.
 */
class TvShowRepositoryImpl(
  private val localData: TvShowData
) : TvShowRepository {

  override fun getTvShows(): Observable<MutableList<Tv>> {
    return Observable.just(
        localData.getTvShows().map {
          TvShowMapper().fromLocal(it)
        }.toMutableList()
    )
  }
}