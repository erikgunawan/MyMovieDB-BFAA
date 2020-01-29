package id.ergun.mymoviedb.data.repository.reminder

import id.ergun.mymoviedb.data.local.cache.ReminderCache
import io.reactivex.Single

/**
 * Created by alfacart on 27/12/19.
 */

class ReminderRepositoryImpl(
    private val data: ReminderCache
) : ReminderRepository {

    override fun setDailyReminder(active: Boolean): Single<Unit> {
        return Single.fromCallable { data.setDailyReminder(active) }
    }

    override fun setReleaseTodayReminder(active: Boolean): Single<Unit> {
        return Single.fromCallable { data.setReleaseTodayReminder(active) }
    }

    override fun isActiveDailyReminder(): Boolean {
        return data.isActiveDailyReminder()
    }

    override fun isActiveReleaseTodayReminder(): Boolean {
        return data.isActiveReleaseTodayReminder()
    }
}