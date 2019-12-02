package id.ergun.mymoviedb.ui.module.tv.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.ergun.mymoviedb.data.model.Tv
import id.ergun.mymoviedb.data.repository.tvShow.TvShowRepository

/**
 * Created by erikgunawan on 27/11/19.
 */
class TvShowDetailViewModel(private val repository: TvShowRepository) : ViewModel() {

  var tvShow: MutableLiveData<Tv> = MutableLiveData()

  fun getData(tv: Tv) {
    tvShow.value = tv
  }
}