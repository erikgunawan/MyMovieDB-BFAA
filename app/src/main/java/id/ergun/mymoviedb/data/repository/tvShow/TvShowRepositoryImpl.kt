package id.ergun.mymoviedb.data.repository.tvShow

import android.content.Context
import id.ergun.mymoviedb.R
import id.ergun.mymoviedb.data.remote.AppService
import id.ergun.mymoviedb.data.remote.model.TvResponse
import io.reactivex.Observable

/**
 * Created by erikgunawan on 27/11/19.
 */
class TvShowRepositoryImpl(
    private val context: Context,
    private val remoteData: AppService
) : TvShowRepository {

    override fun getTvShows(): Observable<TvResponse> {
        return remoteData.getTvShows(language = context.getString(R.string.language_param))
  }
}