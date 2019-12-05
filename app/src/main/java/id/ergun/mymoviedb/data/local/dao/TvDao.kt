package id.ergun.mymoviedb.data.local.dao

import androidx.room.*
import id.ergun.mymoviedb.data.local.model.Tv

/**
 * Created by erikgunawan on 04/12/19.
 */
@Dao
interface TvDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShow(tv: Tv)

    @Update
    fun updateTvShow(tv: Tv)

    @Delete
    fun deleteTvShow(tv: Tv)

    @Query("SELECT * FROM Tv")
    fun getTvShows(): MutableList<Tv>


    @Query("SELECT * FROM Tv WHERE id==:id")
    fun getTvShow(id: String): Tv
}