package id.ergun.mymoviedb.data.pageDataSource.movie

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import id.ergun.mymoviedb.data.dataSource.movie.MovieDataSource
import id.ergun.mymoviedb.data.local.dao.MovieDao
import id.ergun.mymoviedb.data.model.Movie

/**
 * Created by alfacart on 31/12/19.
 */

class MoviePageDataSourceFactory(
    private val dataSource: MovieDataSource,
    private val dao: MovieDao
) : DataSource.Factory<Int, Movie>() {

    private val liveData = MutableLiveData<MoviePageDataSource>()

    override fun create(): DataSource<Int, Movie> {
        val source =
            MoviePageDataSource(
                dataSource,
                dao
            )
        liveData.postValue(source)
        return source
    }

    companion object {
        private const val PAGE_SIZE = 5

        fun pagedListConfig() = PagedList.Config.Builder()
            .setInitialLoadSizeHint(PAGE_SIZE)
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(true)
            .build()
    }

}