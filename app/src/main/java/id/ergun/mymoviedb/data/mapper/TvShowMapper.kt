package id.ergun.mymoviedb.data.mapper

import id.ergun.mymoviedb.data.local.model.Tv

class TvShowMapper {

  fun fromLocal(tv: Tv): id.ergun.mymoviedb.data.model.Tv {
    return id.ergun.mymoviedb.data.model.Tv(
        id = tv.id,
        name = tv.name,
        image = tv.image,
        overview = tv.overview
    )
  }
}