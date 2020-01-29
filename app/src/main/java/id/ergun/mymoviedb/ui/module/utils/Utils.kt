package id.ergun.mymoviedb.ui.module.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by alfacart on 28/01/20.
 */
object Utils {

    fun millisToDateString(
        millisecond: Long,
        format: String,
        timeZone: TimeZone? = TimeZone.getDefault()
    ): String {
        val formatter = SimpleDateFormat(format, Locale("in"))
        formatter.timeZone = timeZone
        return formatter.format(Date(millisecond))
    }
}