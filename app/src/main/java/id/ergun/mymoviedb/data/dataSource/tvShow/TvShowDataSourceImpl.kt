package id.ergun.mymoviedb.data.dataSource.tvShow

import android.content.Context
import id.ergun.mymoviedb.R
import id.ergun.mymoviedb.data.remote.AppService
import id.ergun.mymoviedb.data.remote.model.TvResponse
import io.reactivex.Observable

/**
 * Created by alfacart on 03/02/20.
 */

class TvShowDataSourceImpl(
    private val context: Context,
    private val remoteData: AppService
) : TvShowDataSource {

    override fun getTvShows(page: Int): Observable<TvResponse> {
        return remoteData.getTvShows(
            language = context.getString(R.string.language_param),
            page = page
        )
    }

    override fun getTvDetail(id: String): Observable<TvResponse.Result> {
        return remoteData.getTvDetail(
            language = context.getString(R.string.language_param),
            id = id
        )
    }

    override fun searchTvShow(page: Int, keyword: String): Observable<TvResponse> {
        return remoteData.searchTvShow(
            language = context.getString(R.string.language_param),
            query = keyword,
            page = page
        )
    }
}