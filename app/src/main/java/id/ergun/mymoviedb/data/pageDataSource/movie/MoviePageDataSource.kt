package id.ergun.mymoviedb.data.pageDataSource.movie

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import id.ergun.mymoviedb.data.Const
import id.ergun.mymoviedb.data.dataSource.movie.MovieDataSource
import id.ergun.mymoviedb.data.local.dao.MovieDao
import id.ergun.mymoviedb.data.model.Movie
import io.reactivex.Completable
import io.reactivex.functions.Action

/**
 * Created by alfacart on 31/12/19.
 */

class MoviePageDataSource(
    private val remoteData: MovieDataSource,
    private val localData: MovieDao
) : PageKeyedDataSource<Int, Movie>() {

    var state: MutableLiveData<Const.State> = MutableLiveData()
    private var retryCompletable: Completable? = null


    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Movie>
    ) {
        updateState(Const.State.LOADING)

        remoteData.getMovies(1, "", "")
//
//        compositeDisposable.add(
//            repository.getMovies(1)
//                .subscribe(
//                    { response ->
//                        updateState(Const.State.DONE)
//                        callback.onResult(response.results,
//                            null,
//                            2
//                        )
//                    },
//                    {
//                        updateState(Const.State.ERROR)
//                        setRetry(Action { loadInitial(params, callback) })
//                    }
//                )
//        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        updateState(Const.State.LOADING)
        remoteData.getMovies(params.key, "", "")
//        compositeDisposable.add(
//            repository.getMovies(params.key)
//                .subscribe(
//                    { response ->
//                        updateState(Const.State.DONE)
//                        callback.onResult(response.results,
//                            params.key + 1
//                        )
//                    },
//                    {
//                        updateState(Const.State.ERROR)
//                        setRetry(Action { loadAfter(params, callback) })
//                    }
//                )
//        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        remoteData.getMovies(params.key, "", "")
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