package id.ergun.mymoviedb.ui.module.movie.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.ergun.mymoviedb.data.mapper.MovieMapper
import id.ergun.mymoviedb.data.model.Movie
import id.ergun.mymoviedb.data.repository.movie.MovieRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by erikgunawan on 26/11/19.
 */
class MovieDetailViewModel(private val repository: MovieRepository) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    var movie: MutableLiveData<Movie> = MutableLiveData()

    fun getData(movie: Movie) {
        this.movie.value = movie
    }

    fun getMovieDetail(id: String) {
        compositeDisposable.add(
            repository.getMovieDetail(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .map { MovieMapper().fromRemote(it) }
                .subscribe(
                    {
                        movie.value = it
                    },
                    {
                    }
                )
        )
    }
}