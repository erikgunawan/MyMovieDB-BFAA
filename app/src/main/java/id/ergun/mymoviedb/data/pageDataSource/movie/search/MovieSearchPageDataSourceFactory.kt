package id.ergun.mymoviedb.data.pageDataSource.movie.search

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import id.ergun.mymoviedb.data.dataSource.movie.MovieDataSource
import id.ergun.mymoviedb.data.local.dao.MovieDao
import id.ergun.mymoviedb.data.model.Movie

/**
 * Created by alfacart on 03/02/20.
 */

class MovieSearchPageDataSourceFactory(
    private val dataSource: MovieDataSource,
    private val dao: MovieDao
) : DataSource.Factory<Int, Movie>() {

    val liveData = MutableLiveData<MovieSearchPageDataSource>()

    var keyword: String = ""

    override fun create(): DataSource<Int, Movie> {
        val source =
            MovieSearchPageDataSource(
                dataSource,
                dao
            )
        source.keyword = this.keyword
        liveData.postValue(source)
        return source
    }

    companion object {
        private const val PAGE_SIZE = 10

        fun pagedListConfig() = PagedList.Config.Builder()
            .setInitialLoadSizeHint(PAGE_SIZE)
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(true)
            .build()
    }
}