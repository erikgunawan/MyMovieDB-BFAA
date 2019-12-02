package id.ergun.mymoviedb.ui.module.movie

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import id.ergun.mymoviedb.R
import id.ergun.mymoviedb.data.model.Movie
import kotlinx.android.synthetic.main.fragment_movie.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by erikgunawan on 27/11/19.
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

  @SuppressLint("SetTextI18n")
  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)

    va_data.displayedChild = 0

    adapter = MovieAdapter(context!!)

    rv_data.layoutManager = LinearLayoutManager(context!!)
    rv_data.setHasFixedSize(true)
    rv_data.adapter = adapter

    movieViewModel.movies.observe(this,
      Observer<MutableList<Movie>> {
        if (it != null) {
          adapter.movies = it
          adapter.notifyDataSetChanged()

          va_data.displayedChild = 1
        } else {
          va_data.displayedChild = 2
          tv_message.text = "Terjadi kesalahan"
        }
      }
    )
    movieViewModel.getMovies()
  }

}