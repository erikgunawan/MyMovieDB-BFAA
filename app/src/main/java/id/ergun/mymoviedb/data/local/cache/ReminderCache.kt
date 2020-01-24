package id.ergun.mymoviedb.data.local.cache

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by alfacart on 27/12/19.
 */
class ReminderCache(context: Context) {

    private val preferences: SharedPreferences

    companion object {
        const val PREF_NAME = "REMINDER_PREFERENCE"
        const val PREF_DAILY_REMINDER = "PREF_DAILY_REMINDER"
        const val PREF_RELEASE_TODAY_REMINDER = "PREF_RELEASE_TODAY_REMINDER"
    }

    init {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun setDailyReminder(status: Boolean) {
        preferences.edit()
            .putBoolean(PREF_DAILY_REMINDER, status)
            .apply()
    }

    fun isActiveDailyReminder(): Boolean {
        return preferences
            .getBoolean(PREF_DAILY_REMINDER, false)
    }

    fun setReleaseTodayReminder(status: Boolean) {
        preferences.edit()
            .putBoolean(PREF_RELEASE_TODAY_REMINDER, status)
            .apply()
    }

    fun isActiveReleaseTodayReminder(): Boolean {
        return preferences
            .getBoolean(PREF_RELEASE_TODAY_REMINDER, false)
    }
}