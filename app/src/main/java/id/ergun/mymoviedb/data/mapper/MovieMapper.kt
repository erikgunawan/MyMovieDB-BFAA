package id.ergun.mymoviedb.data.mapper

import id.ergun.mymoviedb.data.local.model.Movie

class MovieMapper {

    fun fromLocal(movie: Movie): id.ergun.mymoviedb.data.model.Movie {
        return id.ergun.mymoviedb.data.model.Movie(
            id = movie.id,
            title = movie.title,
            image = movie.image,
            overview = movie.overview
        )
    }
}