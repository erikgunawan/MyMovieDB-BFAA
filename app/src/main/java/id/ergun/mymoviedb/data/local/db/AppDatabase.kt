package id.ergun.mymoviedb.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.ergun.mymoviedb.data.Const
import id.ergun.mymoviedb.data.local.dao.MovieDao
import id.ergun.mymoviedb.data.local.dao.TvDao
import id.ergun.mymoviedb.data.local.model.Movie
import id.ergun.mymoviedb.data.local.model.Tv

/**
 * Created by erikgunawan on 03/12/19.
 */
@Database(entities = [Movie::class, Tv::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun tvDao(): TvDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun instance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        Const.DB_NAME
                    ).build()
                }
            }
            return INSTANCE
        }

        fun destroy() {
            INSTANCE = null
        }
    }
}