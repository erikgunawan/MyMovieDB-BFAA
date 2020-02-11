package id.ergun.mymoviedb.data.pageDataSource.tvShow.search

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import id.ergun.mymoviedb.data.Const
import id.ergun.mymoviedb.data.dataSource.tvShow.TvShowDataSource
import id.ergun.mymoviedb.data.local.dao.TvDao
import id.ergun.mymoviedb.data.mapper.TvShowMapper
import id.ergun.mymoviedb.data.model.Tv
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action

/**
 * Created by alfacart on 07/02/20.
 */

class TvShowSearchPageDataSource(
    private val remoteData: TvShowDataSource,
    private val localData: TvDao
) : PageKeyedDataSource<Int, Tv>() {

    var keyword: String = ""

    var state: MutableLiveData<Const.State> = MutableLiveData()
    private var retryCompletable: Completable? = null

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Tv>
    ) {
        if (keyword.isEmpty()) return
        updateState(Const.State.LOADING)

        compositeDisposable.add(
            remoteData.searchTvShow(1, keyword)
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

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Tv>) {
        if (keyword.isEmpty()) return
//        updateState(Const.State.LOADING)

        compositeDisposable.add(
            remoteData.searchTvShow(params.key, keyword)
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
