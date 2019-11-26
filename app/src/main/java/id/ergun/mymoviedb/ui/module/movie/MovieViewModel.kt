package id.ergun.mymoviedb.ui.module.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.ergun.mymoviedb.data.model.Movie
import id.ergun.mymoviedb.data.repository.MovieRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by erikgunawan on 24/11/19.
 */
class MovieViewModel(private val repository: MovieRepository) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    var movies: MutableLiveData<MutableList<Movie>> = MutableLiveData()

    fun getMovies() {
        compositeDisposable.add(
            repository.getMovies().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        movies.value = it
                    },
                    {
                        movies.value = mutableListOf()
                    }
                )
        )
    }
}