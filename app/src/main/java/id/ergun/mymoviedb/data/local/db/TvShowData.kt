package id.ergun.mymoviedb.data.local.db

import id.ergun.mymoviedb.R
import id.ergun.mymoviedb.data.local.model.Tv

/**
 * Created by alfacart on 27/11/19.
 */

class TvShowData {

  fun getTvShows(): MutableList<Tv> {
    return getStaticTvShows()
  }

  private fun getStaticTvShows(): MutableList<Tv> {
    val list: MutableList<Tv> = mutableListOf()
    list.add(
        Tv(
            id = 1412,
            name = R.string.tv_show_arrow_title,
            image = R.drawable.poster_arrow,
            overview = R.string.tv_show_arrow_overview
        )
    )
    list.add(
        Tv(
            id = 12609,
            name = R.string.tv_show_dragon_ball_title,
            image = R.drawable.poster_dragon_ball,
            overview = R.string.tv_show_dragon_ball_overview
        )
    )
    list.add(
        Tv(
            id = 46261,
            name = R.string.tv_show_fairy_tail_title,
            image = R.drawable.poster_fairytail,
            overview = R.string.tv_show_fairy_tail_overview
        )
    )
    list.add(
        Tv(
            id = 1434,
            name = R.string.tv_show_family_guy_title,
            image = R.drawable.poster_family_guy,
            overview = R.string.tv_show_family_guy_overview
        )
    )
    list.add(
        Tv(
            id = 60735,
            name = R.string.tv_show_flash_title,
            image = R.drawable.poster_flash,
            overview = R.string.tv_show_flash_overview
        )
    )
    list.add(
        Tv(
            id = 60708,
            name = R.string.tv_show_gotham_title,
            image = R.drawable.poster_gotham,
            overview = R.string.tv_show_gotham_overview
        )
    )
    list.add(
        Tv(
            id = 1416,
            name = R.string.tv_show_grey_anatomy_title,
            image = R.drawable.poster_grey_anatomy,
            overview = R.string.tv_show_grey_anatomy_overview
        )
    )
    list.add(
        Tv(
            id = 54155,
            name = R.string.tv_show_hanna_title,
            image = R.drawable.poster_hanna,
            overview = R.string.tv_show_hanna_overview
        )
    )
    list.add(
        Tv(
            id = 1412,
            name = R.string.tv_show_iron_fist_title,
            image = R.drawable.poster_iron_fist,
            overview = R.string.tv_show_iron_fist_overview
        )
    )
    list.add(
        Tv(
            id = 31910,
            name = R.string.tv_show_naruto_shippuden_title,
            image = R.drawable.poster_naruto_shipudden,
            overview = R.string.tv_show_naruto_shippuden_overview
        )
    )
    return list
  }
}