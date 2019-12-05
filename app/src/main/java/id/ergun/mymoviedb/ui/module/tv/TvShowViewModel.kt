package id.ergun.mymoviedb.ui.module.tv

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.ergun.mymoviedb.data.mapper.TvShowMapper
import id.ergun.mymoviedb.data.model.Tv
import id.ergun.mymoviedb.data.repository.tvShow.TvShowRepository
import id.ergun.mymoviedb.ui.module.utils.Const
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by erikgunawan on 27/11/19.
 */
class TvShowViewModel(private val repository: TvShowRepository) : ViewModel() {

  private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    var favorite = false

    var tvShows: MutableLiveData<MutableList<Tv>> = MutableLiveData()

    var status: MutableLiveData<Const.DataModel.ErrorType> = MutableLiveData()

    fun loadTvShows() {
        if (favorite) getFavoriteTvShows() else getTvShows()
    }

    private fun getTvShows() {
    compositeDisposable.add(
        repository.getTvShows().subscribeOn(Schedulers.io()).observeOn(
            AndroidSchedulers.mainThread()
        )
            .map { TvShowMapper().fromRemote(it) }
            .subscribe(
                {
                    if (it.isNullOrEmpty()) status.value = Const.DataModel.ErrorType.DATA_NOT_FOUND
                    else status.value = Const.DataModel.ErrorType.DATA_FOUND

                    tvShows.value = it
                },
                {
                    tvShows.value = mutableListOf()
                    status.value = Const.DataModel.ErrorType.EXCEPTION
                }
            )
    )
    }

    private fun getFavoriteTvShows() {
        compositeDisposable.add(
            repository.getFavoriteTvShows().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        if (it.isNullOrEmpty()) status.value =
                            Const.DataModel.ErrorType.DATA_NOT_FOUND
                        else status.value = Const.DataModel.ErrorType.DATA_FOUND

                        tvShows.value = it
                    },
                    {
                        tvShows.value = mutableListOf()
                        status.value = Const.DataModel.ErrorType.EXCEPTION
                    }
                )
        )
    }
}