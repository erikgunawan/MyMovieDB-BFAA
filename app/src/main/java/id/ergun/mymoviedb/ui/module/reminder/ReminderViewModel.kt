package id.ergun.mymoviedb.ui.module.reminder

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.ergun.mymoviedb.data.repository.reminder.ReminderRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by alfacart on 27/12/19.
 */

class ReminderViewModel(private val repository: ReminderRepository) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    var isActiveDailyReminder: MutableLiveData<Boolean> = MutableLiveData()
    var isActiveDailyReminderChange: Boolean = false

    var isActiveReleaseTodayReminder: MutableLiveData<Boolean> = MutableLiveData()
    var isActiveReleaseTodayReminderChange: Boolean = false

    fun getReminder() {
        getDailyReminder()
        getReleaseTodayReminder()
    }

    private fun getDailyReminder() {
        compositeDisposable.add(
            repository.isActiveDailyReminder().subscribeOn(Schedulers.io()).observeOn(
                AndroidSchedulers.mainThread()
            )
                .subscribe(
                    {
                        isActiveDailyReminderChange = false
                        isActiveDailyReminder.value = it
                    },
                    {
                        isActiveDailyReminderChange = false
                        isActiveDailyReminder.value = false
                    }
                )
        )
    }

    private fun getReleaseTodayReminder() {
        compositeDisposable.add(
            repository.isActiveReleaseTodayReminder().subscribeOn(Schedulers.io()).observeOn(
                AndroidSchedulers.mainThread()
            )
                .subscribe(
                    {
                        isActiveReleaseTodayReminderChange = false
                        isActiveReleaseTodayReminder.value = it
                    },
                    {
                        isActiveReleaseTodayReminderChange = false
                        isActiveReleaseTodayReminder.value = false
                    }
                )
        )
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