package id.ergun.mymoviedb.ui.module.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import id.ergun.mymoviedb.data.mapper.MovieMapper
import id.ergun.mymoviedb.data.model.Movie
import id.ergun.mymoviedb.data.pageDataSource.movie.MoviePageDataSourceFactory
import id.ergun.mymoviedb.data.repository.movie.MovieRepository
import id.ergun.mymoviedb.ui.module.utils.Const
import id.ergun.mymoviedb.ui.module.utils.Utils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*

/**
 * Created by erikgunawan on 24/11/19.
 */
class MovieViewModel(
    private val repository: MovieRepository,
    private val factory: MoviePageDataSourceFactory
) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    var favorite = false

    var movies: MutableLiveData<MutableList<Movie>> = MutableLiveData()

    var newsList: LiveData<PagedList<Movie>> =
        LivePagedListBuilder(factory, MoviePageDataSourceFactory.pagedListConfig()).build()

    var status: MutableLiveData<Const.DataModel.ErrorType> = MutableLiveData()

    var page: Int = Const.DEFAULT_PAGE

    var calendar: Calendar = Calendar.getInstance()

    fun loadMovies() {
        if (favorite) getFavoriteMovies() else getMovies()
    }

    private fun getMovies() {
        compositeDisposable.add(
            repository.getMovies(
                page, Utils.millisToDateString(calendar.timeInMillis, "yyyy-MM-dd"),
                Utils.millisToDateString(calendar.timeInMillis, "yyyy-MM-dd")
            ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .map { MovieMapper().fromRemote(it) }
                .subscribe(
                    {
                        if (page == 1) {
                            if (it.isNullOrEmpty()) status.value =
                                Const.DataModel.ErrorType.DATA_NOT_FOUND
                            else status.value = Const.DataModel.ErrorType.DATA_FOUND
                            movies.value = it
                        } else
                            movies.value?.addAll(it)
                    },
                    {
                        if (page == 1) {
                            movies.value = mutableListOf()
                            status.value = Const.DataModel.ErrorType.EXCEPTION
                        }
                    }
                )
        )
    }

    private fun getFavoriteMovies() {
        compositeDisposable.add(
            repository.getFavoriteMovies().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        if (it.isNullOrEmpty()) status.value =
                            Const.DataModel.ErrorType.DATA_NOT_FOUND
                        else status.value = Const.DataModel.ErrorType.DATA_FOUND

                        movies.value = it
                    },
                    {
                        movies.value = mutableListOf()
                        status.value = Const.DataModel.ErrorType.EXCEPTION
                    }
                )
        )
    }
}