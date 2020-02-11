package id.ergun.mymoviedb.data.pageDataSource.tvShow

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import id.ergun.mymoviedb.data.Const
import id.ergun.mymoviedb.data.dataSource.tvShow.TvShowDataSource
import id.ergun.mymoviedb.data.local.dao.TvDao
import id.ergun.mymoviedb.data.mapper.TvShowMapper
import id.ergun.mymoviedb.data.model.Tv
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action

/**
 * Created by alfacart on 03/02/20.
 */

class TvShowPageDataSource(
    private val remoteData: TvShowDataSource,
    private val localData: TvDao
) : PageKeyedDataSource<Int, Tv>() {

    var favorite: Boolean = false

    var state: MutableLiveData<Const.State> = MutableLiveData()

    private var retryCompletable: Completable? = null

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Tv>
    ) {
        updateState(Const.State.LOADING)

        if (favorite) {

            compositeDisposable.add(
                Observable.fromCallable {
                    localData.getTvShows().map {
                        TvShowMapper().fromLocal(it)
                    }.toMutableList()
                }.subscribe(
                    {
                        updateState(Const.State.DONE)
                        callback.onResult(it, null, 2)
                    }, {
                        updateState(Const.State.ERROR)
                        setRetry(Action { loadInitial(params, callback) })
                    }
                )
            )
        } else {
            compositeDisposable.add(
                remoteData.getTvShows(1)
                    .subscribe(
                        { response ->
                            updateState(Const.State.DONE)
                            callback.onResult(
                                TvShowMapper().fromRemote(response),
                                null,
                                2
                            )
                        },
                        {
                            updateState(Const.State.ERROR)
                            setRetry(Action { loadInitial(params, callback) })
                        }
                    )
            )
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Tv>) {
//        updateState(Const.State.LOADING)

        if (favorite) {

        } else {
            compositeDisposable.add(
                remoteData.getTvShows(params.key)
                    .subscribe(
                        { response ->
                            updateState(Const.State.DONE)
                            callback.onResult(
                                TvShowMapper().fromRemote(response),
                                params.key + 1
                            )
                        },
                        {
                            //                        updateState(Const.State.ERROR)
                            setRetry(Action { loadAfter(params, callback) })
                        }
                    )
            )
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Tv>) {
        //
    }

    private fun updateState(state: Const.State) {
        this.state.postValue(state)
    }

    fun retry() {
        if (retryCompletable != null) {
//            compositeDisposable.add(retryCompletable!!
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe())
        }
    }

    private fun setRetry(action: Action?) {
        retryCompletable = if (action == null) null else Completable.fromAction(action)
    }

}