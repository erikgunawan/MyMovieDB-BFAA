package id.ergun.mymoviedb.data.repository.movie

import id.ergun.mymoviedb.data.local.db.MovieData
import id.ergun.mymoviedb.data.mapper.MovieMapper
import id.ergun.mymoviedb.data.model.Movie
import io.reactivex.Observable

/**
 * Created by erikgunawan on 24/11/19.
 */
class MovieRepositoryImpl(
    private val localData: MovieData
) : MovieRepository {

    override fun getMovies(): Observable<MutableList<Movie>> {

        return Observable.just(
            localData.getMovies().map {
                MovieMapper().fromLocal(it)
            }.toMutableList()
        )
    }
}