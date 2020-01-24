package id.ergun.mymoviedb.data

/**
 * Created by erikgunawan on 28/11/19.
 */
object Const {


    const val API_KEY = "api_key"

    const val DB_NAME = "my-moviedb"

    const val LANGUAGE = "language"
    const val LANGUAGE_EN_US = "en-US"
    const val LANGUAGE_IN_ID = "id-ID"

    const val QUERY = "query"
    const val PRIMARY_RELEASE_DATE = "primary_release_date"

    enum class State {
        DONE, LOADING, ERROR
    }
}