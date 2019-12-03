package id.ergun.mymoviedb.data.local.dao

import androidx.room.*
import id.ergun.mymoviedb.data.local.model.Movie

/**
 * Created by alfacart on 03/12/19.
 */
@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: Movie)

    @Update
    fun updateMovie(movie: Movie)

    @Delete
    fun deleteMovie(movie: Movie)

    @Query("SELECT * FROM Movie")
    fun getMovies(): MutableList<Movie>
}