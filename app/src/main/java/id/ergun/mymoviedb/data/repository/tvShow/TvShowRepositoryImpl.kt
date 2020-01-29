package id.ergun.mymoviedb.data.repository.tvShow

import android.content.Context
import id.ergun.mymoviedb.R
import id.ergun.mymoviedb.data.local.dao.TvDao
import id.ergun.mymoviedb.data.mapper.TvShowMapper
import id.ergun.mymoviedb.data.model.Tv
import id.ergun.mymoviedb.data.remote.AppService
import id.ergun.mymoviedb.data.remote.model.TvResponse
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by erikgunawan on 27/11/19.
 */
class TvShowRepositoryImpl(
    private val context: Context,
    private val remoteData: AppService,
    private val localData: TvDao
) : TvShowRepository {

    override fun getTvShows(): Observable<TvResponse> {
        return remoteData.getTvShows(language = context.getString(R.string.language_param))
  }

    override fun getTvDetail(id: String): Observable<TvResponse.Result> {
        return remoteData.getTvDetail(
            language = context.getString(R.string.language_param),
            id = id
        )
    }

    override fun getFavoriteTvShows(): Observable<MutableList<Tv>> {
        return Observable.fromCallable {
            localData.getTvShows().map {
                TvShowMapper().fromLocal(it)
            }.toMutableList()
        }
    }

    override fun getFavoriteTvShow(id: String): Observable<Tv> {
        return Observable.fromCallable { localData.getTvShow(id) }
            .map { TvShowMapper().fromLocal(it) }
    }

    override fun addToFavorite(tv: Tv): Single<Unit> {
        return Single.fromCallable {
            localData.insertTvShow(TvShowMapper().toLocal(tv))
        }
    }

    override fun removeFromFavorite(tv: Tv): Single<Unit> {
        return Single.fromCallable {
            localData.deleteTvShow(TvShowMapper().toLocal(tv))
        }
    }

    override fun getSearchTvShow(keyword: String): Observable<TvResponse.Result> {
        return remoteData.searchTvShow(
            language = context.getString(R.string.language_param),
            query = keyword
        )
    }
}