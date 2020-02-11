package id.ergun.mymoviedb.ui.module.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import id.ergun.mymoviedb.data.model.Tv
import id.ergun.mymoviedb.data.pageDataSource.tvShow.TvShowPageDataSource
import id.ergun.mymoviedb.data.pageDataSource.tvShow.TvShowPageDataSourceFactory
import id.ergun.mymoviedb.data.repository.tvShow.TvShowRepository
import id.ergun.mymoviedb.ui.module.utils.Const

/**
 * Created by erikgunawan on 27/11/19.
 */
class TvShowViewModel(
    private val repository: TvShowRepository,
    private val factory: TvShowPageDataSourceFactory
) : ViewModel() {

    var favorite = false

    var tvShows: LiveData<PagedList<Tv>> =
        LivePagedListBuilder(factory, TvShowPageDataSourceFactory.pagedListConfig()).build()

    var tvShowState: LiveData<id.ergun.mymoviedb.data.Const.State> =
        Transformations.switchMap<TvShowPageDataSource, id.ergun.mymoviedb.data.Const.State>(
            factory.liveData,
            TvShowPageDataSource::state
        )

    var status: MutableLiveData<Const.DataModel.ErrorType> = MutableLiveData()

    fun start() {
        checkFavorite(favorite)
    }

    private fun checkFavorite(favorite: Boolean) {
        factory.favorite = favorite
    }

    var keyword: String = ""

    fun refresh() {
        factory.liveData.value?.invalidate()
    }
}