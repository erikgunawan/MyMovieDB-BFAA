package id.ergun.mymoviedb.ui.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import id.ergun.mymoviedb.ui.module.utils.Const.DAILY_REMINDER_HOUR
import id.ergun.mymoviedb.ui.module.utils.Const.RELEASE_TODAY_REMINDER_HOUR
import id.ergun.mymoviedb.ui.notification.ReminderService.Companion.REPLY_ACTION
import java.util.*

/**
 * Created by alfacart on 17/01/20.
 */
class ReminderReceiver : BroadcastReceiver() {

    companion object {

        const val RELEASE_TODAY_REMINDER_ID = 11
        const val DAILY_REMINDER_ID = 12

        fun getReplyMessageIntent(context: Context, notificationId: Int, messageId: Int): Intent {
            val intent = Intent(context, ReminderReceiver::class.java)
            intent.action = REPLY_ACTION
//            intent.putExtra(KEY_NOTIFICATION_ID, notificationId)
//            intent.putExtra(KEY_MESSAGE_ID, messageId)
            return intent
        }

        private fun getPendingIntent(context: Context): PendingIntent? {
            val intent =
                Intent(context, ReminderReceiver::class.java)
            return PendingIntent.getBroadcast(
                context,
                10,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )
        }

    }

    fun setDailyReminderAlarm(context: Context) {
        cancelAlarm(context)
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val calendar = Calendar.getInstance()
        calendar[Calendar.HOUR_OF_DAY] = DAILY_REMINDER_HOUR
        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            getPendingIntent(context)
        )
        Toast.makeText(context, "Repeating alarm set up", Toast.LENGTH_SHORT).show()
    }

    fun setReleaseTodayReminderAlarm(context: Context) {
        cancelAlarm(context)
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val calendar = Calendar.getInstance()
        calendar[Calendar.HOUR_OF_DAY] = RELEASE_TODAY_REMINDER_HOUR
        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            getPendingIntent(context)
        )
        Toast.makeText(context, "Repeating alarm set up", Toast.LENGTH_SHORT).show()
    }

    fun cancelAlarm(context: Context) {
        val alarmManager =
            context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent =
            Intent(context, ReminderReceiver::class.java)
        val pendingIntent =
            PendingIntent.getBroadcast(context, 101, intent, PendingIntent.FLAG_CANCEL_CURRENT)
        alarmManager.cancel(pendingIntent)
    }

    override fun onReceive(context: Context, intent: Intent) {

        val service = Intent(context, ReminderService::class.java)
        service.putExtra("reason", intent.getStringExtra("reason"))

        context.startService(service)
    }
}