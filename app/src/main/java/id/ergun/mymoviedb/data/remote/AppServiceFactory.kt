package id.ergun.mymoviedb.data.remote

import android.content.Context
import com.readystatesoftware.chuck.ChuckInterceptor
import id.ergun.mymoviedb.BuildConfig.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by erikgunawan on 28/11/19.
 */
class AppServiceFactory(
    private val context: Context
) {

    fun <T> createService(classx: Class<T>, isDebug: Boolean): T {
        val okHttpClient = makeOkHttpClient(
            makeLoggingInterceptor(isDebug)
        )
        return createService(classx, okHttpClient)
    }

    private fun <T> createService(classx: Class<T>, okHttpClient: OkHttpClient): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(classx)
    }

    private fun makeOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(ChuckInterceptor(context))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    private fun makeLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (isDebug)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
        return logging
    }

}