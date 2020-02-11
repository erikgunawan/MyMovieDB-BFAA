package id.ergun.mymoviedb.data.pageDataSource.movie

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import id.ergun.mymoviedb.data.Const
import id.ergun.mymoviedb.data.dataSource.movie.MovieDataSource
import id.ergun.mymoviedb.data.local.dao.MovieDao
import id.ergun.mymoviedb.data.mapper.MovieMapper
import id.ergun.mymoviedb.data.model.Movie
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import java.util.*

/**
 * Created by alfacart on 31/12/19.
 */

class MoviePageDataSource(
    private val remoteData: MovieDataSource,
    private val localData: MovieDao
) : PageKeyedDataSource<Int, Movie>() {

    var state: MutableLiveData<Const.State> = MutableLiveData()

    private var retryCompletable: Completable? = null

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    var calendar: Calendar = Calendar.getInstance()

    var favorite: Boolean = false

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Movie>
    ) {
        updateState(Const.State.LOADING)

        if (favorite) {

            compositeDisposable.add(
                Observable.fromCallable {
                    localData.getMovies().map {
                        MovieMapper().fromLocal(it)
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
                remoteData.getMovies(
                    1, null, null//,
//                    Utils.millisToDateString(calendar.timeInMillis, "yyyy-MM-dd"),
//                    Utils.millisToDateString(calendar.timeInMillis, "yyyy-MM-dd")
                )
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
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        if (favorite) {

        } else {
            compositeDisposable.add(
                remoteData.getMovies(
                    params.key, null, null
//                    Utils.millisToDateString(calendar.timeInMillis, "yyyy-MM-dd"),
//                    Utils.millisToDateString(calendar.timeInMillis, "yyyy-MM-dd")
                )
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