package id.ergun.mymoviedb.data.local.db

import id.ergun.mymoviedb.R
import id.ergun.mymoviedb.data.local.model.Movie

/**
 * Created by erikgunawan on 24/11/19.
 */
class MovieData {

    fun getMovies(): MutableList<Movie> {
        return getStaticMovies()
    }

    private fun getStaticMovies(): MutableList<Movie> {
        val list: MutableList<Movie> = mutableListOf()
        list.add(
            Movie(
                id = 332562,
                title = R.string.movie_a_star_is_born_title,
                image = R.drawable.poster_a_star,
                overview = R.string.movie_a_star_is_born_overview
            )
        )
        list.add(
            Movie(
                id = 297802,
                title = R.string.movie_aquaman_title,
                image = R.drawable.poster_aquaman,
                overview = R.string.movie_aquaman_overview
            )
        )
        list.add(
            Movie(
                id = 299536,
                title = R.string.movie_avenger_infinity_war_title,
                image = R.drawable.poster_avengerinfinity,
                overview = R.string.movie_avenger_infinity_war_overview
            )
        )
        list.add(
            Movie(
                id = 405774,
                title = R.string.movie_bird_box_title,
                image = R.drawable.poster_birdbox,
                overview = R.string.movie_bird_box_overview
            )
        )
        list.add(
            Movie(
                id = 424694,
                title = R.string.movie_bohemian_rapsody_title,
                image = R.drawable.poster_bohemian,
                overview = R.string.movie_bohemian_rapsody_overview
            )
        )
        list.add(
            Movie(
                id = 424783,
                title = R.string.movie_bumblebee_title,
                image = R.drawable.poster_bumblebee,
                overview = R.string.movie_bumblebee_overview
            )
        )
        list.add(
            Movie(
                id = 480530,
                title = R.string.movie_creed_ii_title,
                image = R.drawable.poster_creed,
                overview = R.string.movie_creed_ii_overview
            )
        )
        list.add(
            Movie(
                id = 293660,
                title = R.string.movie_deadpool_title,
                image = R.drawable.poster_deadpool,
                overview = R.string.movie_deadpool_overview
            )
        )
        list.add(
            Movie(
                id = 166428,
                title = R.string.movie_how_to_train_your_dragon_title,
                image = R.drawable.poster_dragon,
                overview = R.string.movie_how_to_train_your_dragon_overview
            )
        )
        list.add(
            Movie(
                id = 503314,
                title = R.string.movie_dragon_ball_super_broly_title,
                image = R.drawable.poster_dragonball,
                overview = R.string.movie_dragon_ball_super_broly_overview
            )
        )
        list.add(
            Movie(
                id = 450465,
                title = R.string.movie_glass_title,
                image = R.drawable.poster_glass,
                overview = R.string.movie_glass_overview
            )
        )
        list.add(
            Movie(
                id = 399402,
                title = R.string.movie_hunter_killer_title,
                image = R.drawable.poster_hunterkiller,
                overview = R.string.movie_hunter_killer_overview
            )
        )
        return list
    }
}