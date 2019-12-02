package id.ergun.mymoviedb.data.mapper

import id.ergun.mymoviedb.data.Const
import id.ergun.mymoviedb.data.model.Movie
import id.ergun.mymoviedb.data.remote.model.MovieResponse

class MovieMapper {

    fun fromRemote(response: MovieResponse): MutableList<Movie> {
        val movies = mutableListOf<Movie>()
        response.results?.forEach {
            movies.add(fromRemote(it))
        }

        return movies
    }

    fun fromRemote(movie: MovieResponse.Result): Movie {
        return Movie(
            id = movie.id,
            title = movie.title ?: "",
            posterPath = Const.IMAGE_URL + movie.posterPath,
            originalTitle = movie.originalTitle ?: "",
            overview = movie.overview ?: ""
        )
    }
}