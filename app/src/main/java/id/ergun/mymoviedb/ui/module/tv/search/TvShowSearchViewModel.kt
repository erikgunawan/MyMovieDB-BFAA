package id.ergun.mymoviedb.ui.module.tv.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import id.ergun.mymoviedb.data.Const
import id.ergun.mymoviedb.data.model.Tv
import id.ergun.mymoviedb.data.pageDataSource.tvShow.search.TvShowSearchPageDataSource
import id.ergun.mymoviedb.data.pageDataSource.tvShow.search.TvShowSearchPageDataSourceFactory

/**
 * Created by alfacart on 07/02/20.
 */
class TvShowSearchViewModel(
    private val searchFactory: TvShowSearchPageDataSourceFactory
) : ViewModel() {

    var tvShows: LiveData<PagedList<Tv>> =
        LivePagedListBuilder(
            searchFactory,
            TvShowSearchPageDataSourceFactory.pagedListConfig()
        ).build()

    var tvShowState: LiveData<Const.State> =
        Transformations.switchMap<TvShowSearchPageDataSource, Const.State>(
            searchFactory.liveData,
            TvShowSearchPageDataSource::state
        )

    fun newSearch(keyword: String) {
        searchFactory.keyword = keyword
    }

    fun refresh() {
        searchFactory.liveData.value?.invalidate()
    }
}