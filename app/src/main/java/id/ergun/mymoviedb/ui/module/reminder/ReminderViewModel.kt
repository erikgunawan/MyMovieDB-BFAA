package id.ergun.mymoviedb.ui.module.reminder

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.ergun.mymoviedb.data.mapper.MovieMapper
import id.ergun.mymoviedb.data.model.Movie
import id.ergun.mymoviedb.data.repository.movie.MovieRepository
import id.ergun.mymoviedb.data.repository.reminder.ReminderRepository
import id.ergun.mymoviedb.ui.module.utils.Utils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*

/**
 * Created by alfacart on 27/12/19.
 */

class ReminderViewModel(
    private val repository: ReminderRepository,
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    var isActiveDailyReminder: MutableLiveData<Boolean> = MutableLiveData()
    var isActiveDailyReminderChange: Boolean = false

    var isActiveReleaseTodayReminder: MutableLiveData<Boolean> = MutableLiveData()
    var isActiveReleaseTodayReminderChange: Boolean = false


    var movies: MutableLiveData<MutableList<Movie>> = MutableLiveData()

    var calendar: Calendar = Calendar.getInstance()


    fun getReminder() {
        getDailyReminder()
        getReleaseTodayReminder()
    }

    fun getMovieRelease() {

        compositeDisposable.add(
            movieRepository.getMovies(
                null,
                Utils.millisToDateString(calendar.timeInMillis, "yyyy-MM-dd"),
                Utils.millisToDateString(calendar.timeInMillis, "yyyy-MM-dd")
            ).subscribeOn(Schedulers.io()).observeOn(
                AndroidSchedulers.mainThread()
            )
                .subscribe(
                    { response ->
                        Log.d("response1234", response.toString())
                        movies.value = MovieMapper().fromRemote(response)
//                        updateState(Const.State.DONE)
//                        callback.onResult(
//                            MovieMapper().fromRemote(response),
//                            null, 2
//                        )
                    },
                    {
                        Log.d("response1234", it.toString())
//                        updateState(Const.State.ERROR)
//                        setRetry(Action { loadInitial(params, callback) })
                    }
                )
        )
    }

    private fun getDailyReminder() {
        isActiveDailyReminder.value = repository.isActiveDailyReminder()
    }

    private fun getReleaseTodayReminder() {
        isActiveReleaseTodayReminder.value = repository.isActiveReleaseTodayReminder()
    }

    fun setDailyReminder(active: Boolean) {
        compositeDisposable.add(
            repository.setDailyReminder(active).subscribeOn(Schedulers.io()).observeOn(
                AndroidSchedulers.mainThread()
            )
                .subscribe(
                    {
                        isActiveDailyReminderChange = true
                        isActiveDailyReminder.value = active
                    },
                    {
                    }
                )
        )
    }

    fun setReleaseTodayReminder(active: Boolean) {
        compositeDisposable.add(
            repository.setReleaseTodayReminder(active).subscribeOn(Schedulers.io()).observeOn(
                AndroidSchedulers.mainThread()
            )
                .subscribe(
                    {
                        isActiveReleaseTodayReminderChange = true
                        isActiveReleaseTodayReminder.value = active
                    },
                    {
                    }
                )
        )
    }
}