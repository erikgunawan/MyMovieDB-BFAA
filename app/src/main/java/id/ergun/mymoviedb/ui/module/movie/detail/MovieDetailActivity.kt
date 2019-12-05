package id.ergun.mymoviedb.ui.module.movie.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.ergun.mymoviedb.BuildConfig
import id.ergun.mymoviedb.R
import id.ergun.mymoviedb.data.model.Movie
import id.ergun.mymoviedb.databinding.ActivityMovieDetailBinding
import id.ergun.mymoviedb.ui.module.favorite.FavoriteModel
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.view_toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by erikgunawan on 24/11/19.
 */
class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding

    private val movieViewModel: MovieDetailViewModel by viewModel()

    companion object {
        const val EXTRA_MOVIE = "EXTRA_MOVIE"
        fun newIntent(
            context: Context, movie: Movie
        ): Intent {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(EXTRA_MOVIE, movie)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail)

        setSupportActionBar(toolbar)
        supportActionBar?.run {
            setDisplayShowHomeEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }

        val movie = intent.getParcelableExtra<Movie>(EXTRA_MOVIE)

        movieViewModel.movie.observe(this,
            Observer<Movie> {
                if (it != null) {
                    binding.tvTitle.text = it.title

                    Glide.with(this)
                        .load(BuildConfig.IMAGE_URL + it.posterPath)
                        .apply(RequestOptions.centerInsideTransform())
                        .into(binding.ivPoster)

                    binding.tvOverview.text = it.overview
                } else {
                    Toast.makeText(
                        this,
                        getString(R.string.menu_setting_change_language),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        )

        movieViewModel.favorite.observe(this,
            Observer<Boolean> {
                if (it == null) return@Observer
                fab_favorite.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        if (it) R.drawable.ic_favorite else R.drawable.ic_favorite_unchecked
                    )
                )
            })

        movieViewModel.favoriteStatus.observe(this,
            Observer<FavoriteModel.Type> {
                Toast.makeText(
                    this,
                    if (it == FavoriteModel.Type.ADD_TO_FAVORITE) getString(R.string.message_add_to_favorite)
                    else getString(R.string.message_remove_from_favorite),
                    Toast.LENGTH_SHORT
                ).show()
            })
        movieViewModel.getData(movie!!)
        movieViewModel.getMovieDetail(movie.id.toString())
        movieViewModel.getFavoriteMovie(movie.id.toString())

        fab_favorite.setOnClickListener {
            movieViewModel.updateFavorite()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            R.id.action_change_settings -> {
                val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
                startActivity(mIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_setting, menu)
        return super.onCreateOptionsMenu(menu)
    }

}