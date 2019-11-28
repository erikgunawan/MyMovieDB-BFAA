package id.ergun.mymoviedb.ui.module.movie.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.ergun.mymoviedb.data.model.Movie
import id.ergun.mymoviedb.data.repository.movie.MovieRepository

/**
 * Created by erikgunawan on 26/11/19.
 */
class MovieDetailViewModel(private val repository: MovieRepository) : ViewModel() {

    var movie: MutableLiveData<Movie> = MutableLiveData()

    fun getData(movie: Movie) {
        this.movie.value = movie
    }
}