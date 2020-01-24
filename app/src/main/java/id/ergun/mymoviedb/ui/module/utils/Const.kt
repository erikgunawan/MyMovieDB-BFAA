package id.ergun.mymoviedb.ui.module.utils

/**
 * Created by erikgunawan on 05/12/19.
 */
object Const {

    const val DEFAULT_PAGE = 1

    const val DAILY_REMINDER_HOUR = 7
    const val RELEASE_TODAY_REMINDER_HOUR = 8

    class DataModel {
        enum class ErrorType {
            DATA_FOUND,
            DATA_NOT_FOUND,
            EXCEPTION
        }
    }
}