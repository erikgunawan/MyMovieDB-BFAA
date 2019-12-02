package id.ergun.mymoviedb.ui.module.tv

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.ergun.mymoviedb.data.mapper.TvShowMapper
import id.ergun.mymoviedb.data.model.Tv
import id.ergun.mymoviedb.data.repository.tvShow.TvShowRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by erikgunawan on 27/11/19.
 */
class TvShowViewModel(private val repository: TvShowRepository) : ViewModel() {

  private val compositeDisposable: CompositeDisposable = CompositeDisposable()

  var movies: MutableLiveData<MutableList<Tv>> = MutableLiveData()

  fun getMovies() {
    compositeDisposable.add(
        repository.getTvShows().subscribeOn(Schedulers.io()).observeOn(
            AndroidSchedulers.mainThread()
        )
            .map { TvShowMapper().fromRemote(it) }
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