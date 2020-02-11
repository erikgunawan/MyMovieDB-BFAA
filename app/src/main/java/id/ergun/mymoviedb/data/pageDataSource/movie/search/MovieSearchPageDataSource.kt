package id.ergun.mymoviedb.data.pageDataSource.movie.search

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import id.ergun.mymoviedb.data.Const
import id.ergun.mymoviedb.data.dataSource.movie.MovieDataSource
import id.ergun.mymoviedb.data.local.dao.MovieDao
import id.ergun.mymoviedb.data.mapper.MovieMapper
import id.ergun.mymoviedb.data.model.Movie
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action

/**
 * Created by alfacart on 03/02/20.
 */

class MovieSearchPageDataSource(
    private val remoteData: MovieDataSource,
    private val localData: MovieDao
) : PageKeyedDataSource<Int, Movie>() {

    var keyword: String = ""
    var state: MutableLiveData<Const.State> = MutableLiveData()

    private var retryCompletable: Completable? = null

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Movie>
    ) {
        if (keyword.isEmpty()) return

        updateState(Const.State.LOADING)

        compositeDisposable.add(
            remoteData.searchMovie(1, keyword)
                .subscribe(
                    { response ->
                        updateState(Const.State.DONE)
                        callback.onResult(
                            MovieMapper().fromRemote(response),
                            null, 2
                        )
                    },
                    {
                        updateState(Const.State.ERROR)
                        setRetry(Action { loadInitial(params, callback) })
                    }
                )
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        if (keyword.isEmpty()) return

        compositeDisposable.add(
            remoteData.searchMovie(params.key, keyword)
                .subscribe(
                    { response ->
                        updateState(Const.State.DONE)
                        callback.onResult(
                            MovieMapper().fromRemote(response),
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

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
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