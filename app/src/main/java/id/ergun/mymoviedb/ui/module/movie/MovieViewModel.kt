package id.ergun.mymoviedb.ui.module.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import id.ergun.mymoviedb.data.model.Movie
import id.ergun.mymoviedb.data.pageDataSource.movie.MoviePageDataSource
import id.ergun.mymoviedb.data.pageDataSource.movie.MoviePageDataSourceFactory
import id.ergun.mymoviedb.data.repository.movie.MovieRepository
import id.ergun.mymoviedb.ui.module.utils.Const

/**
 * Created by erikgunawan on 24/11/19.
 */
class MovieViewModel(
    private val repository: MovieRepository,
    private val factory: MoviePageDataSourceFactory
) : ViewModel() {

    var favorite = false
    var search = false

    var movies: MutableLiveData<MutableList<Movie>> = MutableLiveData()

    var status: MutableLiveData<Const.DataModel.ErrorType> = MutableLiveData()

    var page: Int = Const.DEFAULT_PAGE

    fun start() {
        checkFavorite(favorite)
    }

    private fun checkFavorite(favorite: Boolean) {
        factory.favorite = favorite
    }

    var keyword: String = ""

    val movieList: LiveData<PagedList<Movie>> =
        LivePagedListBuilder(factory, MoviePageDataSourceFactory.pagedListConfig()).build()

    val movieState: LiveData<id.ergun.mymoviedb.data.Const.State> =
        Transformations.switchMap<MoviePageDataSource, id.ergun.mymoviedb.data.Const.State>(
            factory.liveData,
            MoviePageDataSource::state
        )

    fun refresh() {
        factory.liveData.value?.invalidate()
    }

}