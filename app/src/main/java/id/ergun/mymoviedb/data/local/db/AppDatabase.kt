package id.ergun.mymoviedb.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.ergun.mymoviedb.data.Const
import id.ergun.mymoviedb.data.local.dao.MovieDao
import id.ergun.mymoviedb.data.local.model.Movie

/**
 * Created by alfacart on 03/12/19.
 */
@Database(entities = [Movie::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

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