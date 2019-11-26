package id.ergun.mymoviedb.ui.module.movie

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import id.ergun.mymoviedb.R
import id.ergun.mymoviedb.data.model.Movie
import id.ergun.mymoviedb.databinding.ActivityMovieBinding
import kotlinx.android.synthetic.main.view_toolbar.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * Created by erikgunawan on 24/11/19.
 */
class MovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieBinding

    private val movieViewModel: MovieViewModel by viewModel()

    private val adapter: MovieAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie)

        setSupportActionBar(toolbar)
        supportActionBar?.run {
            setDisplayShowTitleEnabled(true)
            title = getString(R.string.app_name)
        }

        val rvData = binding.rvData
        rvData.layoutManager = LinearLayoutManager(this)
        rvData.setHasFixedSize(true)
        rvData.adapter = adapter

        movieViewModel.movies.observe(this,
            Observer<MutableList<Movie>> {
                if (it != null) {
                    adapter.movies = it
                    adapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
                }
            }
        )
        movieViewModel.getMovies()
    }
}