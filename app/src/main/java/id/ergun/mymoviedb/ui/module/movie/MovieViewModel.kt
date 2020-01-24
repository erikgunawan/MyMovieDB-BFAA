package id.ergun.mymoviedb.ui.module.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.ergun.mymoviedb.data.mapper.MovieMapper
import id.ergun.mymoviedb.data.model.Movie
import id.ergun.mymoviedb.data.repository.movie.MovieRepository
import id.ergun.mymoviedb.ui.module.utils.Const
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by erikgunawan on 24/11/19.
 */
class MovieViewModel(private val repository: MovieRepository) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    var favorite = false

    var movies: MutableLiveData<MutableList<Movie>> = MutableLiveData()

    var status: MutableLiveData<Const.DataModel.ErrorType> = MutableLiveData()

    var page: Int = Const.DEFAULT_PAGE

    fun loadMovies() {
        if (favorite) getFavoriteMovies() else getMovies()
    }

    private fun getMovies() {
        compositeDisposable.add(
            repository.getMovies(
                page,
                "2019-12-26",
                "2019-12-26"
            ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .map { MovieMapper().fromRemote(it) }
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