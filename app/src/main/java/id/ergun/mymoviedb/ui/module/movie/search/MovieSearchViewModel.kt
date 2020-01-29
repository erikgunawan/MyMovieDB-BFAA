package id.ergun.mymoviedb.ui.module.movie.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.ergun.mymoviedb.data.mapper.MovieMapper
import id.ergun.mymoviedb.data.model.Movie
import id.ergun.mymoviedb.data.repository.movie.MovieRepository
import id.ergun.mymoviedb.data.repository.tvShow.TvShowRepository
import id.ergun.mymoviedb.ui.module.utils.Const
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by alfacart on 28/01/20.
 */

class MovieSearchViewModel(
    private val movieRepository: MovieRepository,
    private val tvShowRepository: TvShowRepository
) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    var movies: MutableLiveData<MutableList<Movie>> = MutableLiveData()

    var status: MutableLiveData<Const.DataModel.ErrorType> = MutableLiveData()

    fun searchMovie(keyword: String) {
        compositeDisposable.add(
            movieRepository.searchMovie(keyword)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
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
}