package id.ergun.mymoviedb.data.repository.reminder

import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by alfacart on 27/12/19.
 */
interface ReminderRepository {

    fun setDailyReminder(active: Boolean): Single<Unit>
    fun setReleaseTodayReminder(active: Boolean): Single<Unit>

    fun isActiveDailyReminder(): Observable<Boolean>
    fun isActiveReleaseTodayReminder(): Observable<Boolean>
}