package id.ergun.mymoviedb.ui.notification

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

/**
 * Created by alfacart on 17/01/20.
 */
class ReleaseTodayWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

    companion object {
        const val NOTIFICATION_ID = "appName_notification_id"
        const val NOTIFICATION_NAME = "appName"
        const val NOTIFICATION_CHANNEL = "appName_channel_01"
        const val NOTIFICATION_WORK = "appName_notification_work"
    }

    override fun doWork(): Result {
        val id = inputData.getLong(NOTIFICATION_ID, 0).toInt()
//        sendNotification(id)

        return Result.success()
    }

}