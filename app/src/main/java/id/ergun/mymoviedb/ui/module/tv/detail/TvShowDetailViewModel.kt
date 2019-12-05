package id.ergun.mymoviedb.ui.module.tv.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.ergun.mymoviedb.data.mapper.TvShowMapper
import id.ergun.mymoviedb.data.model.Tv
import id.ergun.mymoviedb.data.repository.tvShow.TvShowRepository
import id.ergun.mymoviedb.ui.module.favorite.FavoriteModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by erikgunawan on 27/11/19.
 */
class TvShowDetailViewModel(private val repository: TvShowRepository) : ViewModel() {

  private val compositeDisposable: CompositeDisposable = CompositeDisposable()
  var tvShow: MutableLiveData<Tv> = MutableLiveData()

  var favorite: MutableLiveData<Boolean> = MutableLiveData()
  var favoriteStatus: MutableLiveData<FavoriteModel.Type> = MutableLiveData()

  fun getData(tv: Tv) {
    tvShow.value = tv
  }

  fun getFavoriteTv(id: String) {
    compositeDisposable.add(
      repository.getFavoriteTvShow(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        .subscribe({
          favorite.value = true
        }, {
          favorite.value = false
        })
    )
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

  private fun addToFavorite() {
    compositeDisposable.add(
      repository.addToFavorite(tvShow.value!!).subscribeOn(Schedulers.io()).observeOn(
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
      repository.removeFromFavorite(tvShow.value!!).subscribeOn(Schedulers.io()).observeOn(
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