package id.ergun.mymoviedb.ui.notification

import android.app.IntentService
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput
import id.ergun.mymoviedb.R
import id.ergun.mymoviedb.data.model.Movie
import id.ergun.mymoviedb.ui.module.movie.detail.MovieDetailActivity

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

    private var mNotificationId: Int = 0
    private var mMessageId: Int = 0

    override fun onHandleIntent(intent: Intent?) {
        if (intent != null) {
            showNotification()
        }
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

