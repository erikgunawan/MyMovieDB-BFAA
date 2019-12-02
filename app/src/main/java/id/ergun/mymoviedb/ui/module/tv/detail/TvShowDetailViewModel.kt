package id.ergun.mymoviedb.ui.module.tv.detail

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
class TvShowDetailViewModel(private val repository: TvShowRepository) : ViewModel() {

  private val compositeDisposable: CompositeDisposable = CompositeDisposable()
  var tvShow: MutableLiveData<Tv> = MutableLiveData()

  fun getData(tv: Tv) {
    tvShow.value = tv
  }

  fun getTvDetail(id: String) {
    compositeDisposable.add(
      repository.getTvDetail(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        .map { TvShowMapper().fromRemote(it) }
        .subscribe(
          {
            tvShow.value = it
          },
          {
          }
        )
    )
  }
}