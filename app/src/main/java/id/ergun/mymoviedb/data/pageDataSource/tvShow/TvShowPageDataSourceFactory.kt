package id.ergun.mymoviedb.data.pageDataSource.tvShow

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import id.ergun.mymoviedb.data.dataSource.tvShow.TvShowDataSource
import id.ergun.mymoviedb.data.local.dao.TvDao
import id.ergun.mymoviedb.data.model.Tv

/**
 * Created by alfacart on 03/02/20.
 */

class TvShowPageDataSourceFactory(
    private val dataSource: TvShowDataSource,
    private val dao: TvDao
) : DataSource.Factory<Int, Tv>() {

    val liveData = MutableLiveData<TvShowPageDataSource>()

    var favorite: Boolean = false

    override fun create(): DataSource<Int, Tv> {
        val source =
            TvShowPageDataSource(
                dataSource,
                dao
            )
        source.favorite = this.favorite
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