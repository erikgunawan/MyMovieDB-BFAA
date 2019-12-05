package id.ergun.mymoviedb.ui.module.movie.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.ergun.mymoviedb.data.mapper.MovieMapper
import id.ergun.mymoviedb.data.model.Movie
import id.ergun.mymoviedb.data.repository.movie.MovieRepository
import id.ergun.mymoviedb.ui.module.favorite.FavoriteModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by erikgunawan on 26/11/19.
 */
class MovieDetailViewModel(private val repository: MovieRepository) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    var movie: MutableLiveData<Movie> = MutableLiveData()

    var favorite: MutableLiveData<Boolean> = MutableLiveData()

    var favoriteStatus: MutableLiveData<FavoriteModel.Type> = MutableLiveData()

    fun getData(movie: Movie) {
        this.movie.value = movie
    }

    fun getFavoriteMovie(id: String) {
        compositeDisposable.add(
            repository.getFavoriteMovie(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    favorite.value = true
                }, {
                    favorite.value = false
                })
        )
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

    private fun addToFavorite() {
        compositeDisposable.add(
            repository.addToFavorite(movie.value!!).subscribeOn(Schedulers.io()).observeOn(
                AndroidSchedulers.mainThread()
            )
                .subscribe({
                    favorite.value = true
                    favoriteStatus.value = FavoriteModel.Type.ADD_TO_FAVORITE
                }, {
                    Log.d("error", it.message.toString())
                })
        )
    }

    private fun removeFromFavorite() {
        compositeDisposable.add(
            repository.removeFromFavorite(movie.value!!).subscribeOn(Schedulers.io()).observeOn(
                AndroidSchedulers.mainThread()
            )
                .subscribe({
                    favorite.value = false
                    favoriteStatus.value = FavoriteModel.Type.REMOVE_FROM_FAVORITE
                }, {
                    Log.d("error", it.message.toString())
                })
        )
    }

    fun updateFavorite() {
        if (favorite.value == true) {
            removeFromFavorite()
        } else {
            addToFavorite()
        }
    }
}