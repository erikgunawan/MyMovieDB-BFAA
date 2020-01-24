package id.ergun.mymoviedb.ui.module.favorite.stackWidget

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.util.Log
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import androidx.core.os.bundleOf
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import id.ergun.mymoviedb.BuildConfig
import id.ergun.mymoviedb.R
import id.ergun.mymoviedb.data.local.dao.MovieDao
import id.ergun.mymoviedb.data.local.db.AppDatabase
import id.ergun.mymoviedb.data.mapper.MovieMapper
import id.ergun.mymoviedb.data.model.Movie
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import java.util.*

/**
 * Created by alfacart on 30/12/19.
 */

internal class FavoriteStackRemoteViewsFactory(private val mContext: Context, val intent: Intent) :
    RemoteViewsService.RemoteViewsFactory {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val mWidgetItems = ArrayList<Bitmap>()

    var favorites: MutableList<Movie> = mutableListOf()

    lateinit var movieDao: MovieDao

    override fun onCreate() {

    }

    override fun onDataSetChanged() {
        //Ini berfungsi untuk melakukan refresh saat terjadi perubahan.

        movieDao = AppDatabase.instance(mContext)!!.movieDao()

        getFavoriteMovies()
    }

    private fun getFavoriteMovies() {
        val x = Observable.fromCallable {
            movieDao.getMovies().map {
                MovieMapper().fromLocal(it)
            }.toMutableList()
        }
        compositeDisposable.add(
            x.subscribe {
                favorites.clear()
                favorites.addAll(it)
            })

    }

    override fun onDestroy() {

    }

    override fun getCount(): Int = favorites.size

    override fun getViewAt(position: Int): RemoteViews {
        val rv = RemoteViews(mContext.packageName, R.layout.item_favorite_widget)
//        rv.setimage(R.id.imageView, mWidgetItems[position])
        Log.d("getviewAt", BuildConfig.IMAGE_URL + favorites[position].posterPath)
        val bitmap: Bitmap = Glide.with(mContext)
            .asBitmap()
            .load(BuildConfig.IMAGE_URL + favorites[position].posterPath)
            .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
            .get()

//                    onComplete.invoke(resource)
        rv.setImageViewBitmap(R.id.imageView, bitmap)

//        rv.setImageViewUri(R.id.imageView, Uri.parse(BuildConfig.IMAGE_URL + favorites[position].posterPath))
//        rv.setImageViewResource(R.id.imageView, R.drawable.ic_search)
        val extras = bundleOf(
            FavoriteBannerWidget.EXTRA_ITEM to favorites[position]
        )
        val fillInIntent = Intent()
        fillInIntent.putExtras(extras)

        rv.setOnClickFillInIntent(R.id.imageView, fillInIntent)
        return rv
    }

    override fun getLoadingView(): RemoteViews? = null

    override fun getViewTypeCount(): Int = 1

    override fun getItemId(i: Int): Long = 0

    override fun hasStableIds(): Boolean = false

}