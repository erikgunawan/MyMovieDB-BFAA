package id.ergun.mymoviedb.ui.notification

import android.app.IntentService
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput
import id.ergun.mymoviedb.R
import id.ergun.mymoviedb.data.mapper.MovieMapper
import id.ergun.mymoviedb.data.model.Movie
import id.ergun.mymoviedb.data.repository.movie.MovieRepository
import id.ergun.mymoviedb.ui.module.main.MainActivity
import id.ergun.mymoviedb.ui.module.movie.detail.MovieDetailActivity
import id.ergun.mymoviedb.ui.module.utils.Utils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.inject
import java.util.*

/**
 * Created by alfacart on 17/01/20.
 */
class ReminderService : IntentService("NotificationService") {


    companion object {
        private const val KEY_REPLY = "key_reply_message"
        const val REPLY_ACTION = "com.dicoding.notification.directreply.REPLY_ACTION"
        const val CHANNEL_ID = "channel_01"
        val CHANNEL_NAME: CharSequence = "dicoding channel"

        fun getReplyMessage(intent: Intent): CharSequence? {
            val remoteInput = RemoteInput.getResultsFromIntent(intent)
            return remoteInput?.getCharSequence(KEY_REPLY)
        }
    }

    val movieRepository: MovieRepository by inject()

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    var calendar: Calendar = Calendar.getInstance()

    private var mNotificationId: Int = 0
    private var mMessageId: Int = 0

    override fun onHandleIntent(intent: Intent?) {
        if (intent != null) {
            showNotification()
        }

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
                        val mapper = MovieMapper().fromRemote(response)
                        for (movie: Movie in mapper) {
                            if (movie.id != null)
                                updateNotification(movie.id.toInt(), movie)
                        }
//                        movies.value = MovieMapper().fromRemote(response)
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

    private fun updateNotification(notifyId: Int, movie: Movie) {
        val notificationManagerCompat =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val builder = NotificationCompat.Builder(this, "channel_01").apply {
            setSmallIcon(R.drawable.ic_movies)
            setContentTitle(movie.title)
            setContentText(movie.overview)

            val intent = MainActivity.newIntentToMovieDetail(this@ReminderService, movie)

            val pendingIntent = PendingIntent.getActivity(
                this@ReminderService,
                notifyId,
                intent,
                PendingIntent.FLAG_CANCEL_CURRENT
            )
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
    private fun showNotification() {
        mNotificationId = 1
        mMessageId = 123

        // Tambahkan channel id, channel name , dan tingkat importance

        val replyLabel = getString(R.string.message_add_to_favorite)
        val remoteInput = RemoteInput.Builder(KEY_REPLY)
            .setLabel(replyLabel)
            .build()

        val replyAction = NotificationCompat.Action.Builder(
            R.drawable.ic_favorite, replyLabel, getReplyPendingIntent()
        )
            .addRemoteInput(remoteInput)
            .setAllowGeneratedReplies(true)
            .build()

        val mBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_reminder)
            .setContentTitle(getString(R.string.menu_setting_reminder))
            .setContentText(getString(R.string.message_error_universal))
            .setShowWhen(true)
            .addAction(replyAction)

        val mNotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        /*
        Untuk android Oreo ke atas perlu menambahkan notification channel
        Materi ini akan dibahas lebih lanjut di modul extended
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            /* Create or update. */
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )

            mBuilder.setChannelId(CHANNEL_ID)

            mNotificationManager.createNotificationChannel(channel)
        }

        val notification = mBuilder.build()

        mNotificationManager.notify(mNotificationId, notification)
    }

    private fun getReplyPendingIntent(): PendingIntent {
        val intent: Intent
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent = ReminderReceiver.getReplyMessageIntent(this, mNotificationId, mMessageId)
            PendingIntent.getBroadcast(
                applicationContext,
                100,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )
        } else {
            intent = MovieDetailActivity.newIntent(this, Movie())
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            PendingIntent.getActivity(this, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        }
    }
}

