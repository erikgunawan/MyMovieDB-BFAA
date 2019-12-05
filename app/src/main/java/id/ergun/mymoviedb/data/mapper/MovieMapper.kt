package id.ergun.mymoviedb.data.mapper

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
            posterPath = movie.posterPath ?: "",
            overview = movie.overview ?: ""
        )
    }


    fun fromLocal(movie: id.ergun.mymoviedb.data.local.model.Movie): Movie {
        return Movie(
            id = movie.id,
            title = movie.title,
            posterPath = movie.posterPath,
            overview = movie.overview
        )
    }


    fun toLocal(movie: Movie): id.ergun.mymoviedb.data.local.model.Movie {
        return id.ergun.mymoviedb.data.local.model.Movie(
            id = movie.id ?: 0,
            title = movie.title,
            posterPath = movie.posterPath,
            overview = movie.overview
        )
    }
}