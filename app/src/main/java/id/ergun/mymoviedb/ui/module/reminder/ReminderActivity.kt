package id.ergun.mymoviedb.ui.module.reminder

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import id.ergun.mymoviedb.R
import id.ergun.mymoviedb.data.model.Movie
import id.ergun.mymoviedb.databinding.ActivityReminderBinding
import id.ergun.mymoviedb.ui.module.main.MainActivity
import id.ergun.mymoviedb.ui.module.movie.MovieViewModel
import id.ergun.mymoviedb.ui.notification.ReminderReceiver
import kotlinx.android.synthetic.main.activity_reminder.*
import kotlinx.android.synthetic.main.view_toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

/**
 * Created by alfacart on 27/12/19.
 */

class ReminderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReminderBinding

    private val reminderViewModel: ReminderViewModel by viewModel()
    private val movieViewModel: MovieViewModel by viewModel()

    companion object {
        fun newIntent(
            context: Context
        ): Intent {
            return Intent(context, ReminderActivity::class.java)
        }
    }

    var notifyId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_reminder)

        setSupportActionBar(toolbar)
        supportActionBar?.run {
            title = getString(R.string.menu_setting_reminder)
            setDisplayShowHomeEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }

        switch_daily.setOnClickListener {
            reminderViewModel.setDailyReminder(switch_daily.isChecked)
        }

        switch_release_today.setOnClickListener {
            reminderViewModel.setReleaseTodayReminder(switch_release_today.isChecked)
        }

        reminderViewModel.getReminder()

        reminderViewModel.isActiveDailyReminder.observe(this,
            Observer<Boolean> {
                switch_daily.isChecked = it

                if (reminderViewModel.isActiveDailyReminderChange) {
                    if (it)
                        ReminderReceiver().setDailyReminderAlarm(this)
                }
            }
        )

        reminderViewModel.isActiveReleaseTodayReminder.observe(this,
            Observer<Boolean> {
                switch_release_today.isChecked = it
                if (reminderViewModel.isActiveReleaseTodayReminderChange) {
                    if (it)
                        ReminderReceiver().setReleaseTodayReminderAlarm(this)
                }
            }
        )

        movieViewModel.movies.observe(this,
            Observer<MutableList<Movie>> {
                if (!it.isNullOrEmpty()) {
                    it.forEach { movie ->
                        updateNotification(movie.id!!.toInt(), movie)
                    }
                }
            }
        )

    }

    private val mNotificationTime =
        Calendar.getInstance().timeInMillis + 5000 //Set after 5 seconds from the current time.

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateNotification(notifyId: Int, movie: Movie) {
        val notificationManagerCompat =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val builder = NotificationCompat.Builder(this, "channel_01").apply {
            setSmallIcon(R.drawable.ic_movies)
            setContentTitle(movie.title)
            setContentText(movie.overview)

            // Launches the app to open the reminder edit screen when tapping the whole notification
            val intent = Intent(this@ReminderActivity, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }

            val pendingIntent = PendingIntent.getActivity(this@ReminderActivity, 0, intent, 0)
            setContentIntent(pendingIntent)
        }

        /*
        Untuk android Oreo ke atas perlu menambahkan notification channel
        Materi ini akan dibahas lebih lanjut di modul extended
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            /* Create or update. */
            val channel = NotificationChannel(
                "channel_01",
                "channel_01",
                NotificationManager.IMPORTANCE_DEFAULT
            )

            channel.enableVibration(true)
            channel.vibrationPattern = longArrayOf(1000, 1000, 1000, 1000, 1000)

            builder.setChannelId("channel_01")

            notificationManagerCompat.createNotificationChannel(channel)
        }

        val notification = builder.build()

        notificationManagerCompat.notify(notifyId, notification)


    }
}