package id.ergun.mymoviedb.ui.module.movie.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import id.ergun.mymoviedb.data.model.Movie
import id.ergun.mymoviedb.data.pageDataSource.movie.search.MovieSearchPageDataSource
import id.ergun.mymoviedb.data.pageDataSource.movie.search.MovieSearchPageDataSourceFactory

/**
 * Created by alfacart on 28/01/20.
 */

class MovieSearchViewModel(
    private val searchFactory: MovieSearchPageDataSourceFactory
) : ViewModel() {

    var movieList: LiveData<PagedList<Movie>> =
        LivePagedListBuilder(
            searchFactory,
            MovieSearchPageDataSourceFactory.pagedListConfig()
        ).build()

    var movieState: LiveData<id.ergun.mymoviedb.data.Const.State> =
        Transformations.switchMap<MovieSearchPageDataSource, id.ergun.mymoviedb.data.Const.State>(
            searchFactory.liveData,
            MovieSearchPageDataSource::state
        )

    fun newSearch(keyword: String) {
        searchFactory.keyword = keyword
    }

    fun refresh() {
        searchFactory.liveData.value?.invalidate()
    }
}