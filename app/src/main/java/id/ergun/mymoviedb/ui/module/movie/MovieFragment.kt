package id.ergun.mymoviedb.ui.module.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import id.ergun.mymoviedb.R
import id.ergun.mymoviedb.data.model.Movie
import kotlinx.android.synthetic.main.fragment_movie.rv_data
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by alfacart on 27/11/19.
 */
class MovieFragment : Fragment() {

  private val movieViewModel: MovieViewModel by viewModel()

  lateinit var adapter: MovieAdapter

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_movie, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)


    adapter = MovieAdapter(context!!)

    val rvData = rv_data
    rvData.layoutManager = LinearLayoutManager(context!!)
    rvData.setHasFixedSize(true)
    rvData.adapter = adapter

    movieViewModel.movies.observe(this,
        Observer<MutableList<Movie>> {
          if (it != null) {
            adapter.movies = it
            adapter.notifyDataSetChanged()
          } else {
            Toast.makeText(context!!, "error", Toast.LENGTH_SHORT)
                .show()
          }
        }
    )
    movieViewModel.getMovies()
  }

}