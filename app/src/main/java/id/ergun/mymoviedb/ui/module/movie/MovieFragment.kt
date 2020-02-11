package id.ergun.mymoviedb.ui.module.movie

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import id.ergun.mymoviedb.R
import id.ergun.mymoviedb.data.model.Movie
import id.ergun.mymoviedb.ui.module.movie.detail.MovieDetailActivity
import id.ergun.mymoviedb.ui.module.movie.search.MovieSearchViewModel
import kotlinx.android.synthetic.main.fragment_movie.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import id.ergun.mymoviedb.data.Const as C

/**
 * Created by erikgunawan on 27/11/19.
 */
class MovieFragment : Fragment() {

  private val movieViewModel: MovieViewModel by viewModel()

    private val movieSearchViewModel: MovieSearchViewModel by sharedViewModel()

    private lateinit var adapter: PagedMovieAdapter//MovieAdapter

    var search: Boolean = false

  companion object {

    private const val ARGUMENT_FAVORITE = "ARGUMENT_FAVORITE"
      private const val ARGUMENT_SEARCH = "ARGUMENT_SEARCH"

      fun newInstance(favorite: Boolean = false, search: Boolean = false): Fragment {
      val fragment = MovieFragment()
      val argument = Bundle()
      argument.putBoolean(ARGUMENT_FAVORITE, favorite)
          argument.putBoolean(ARGUMENT_SEARCH, search)
          fragment.arguments = argument
          return fragment
      }

      fun newInstanceToDetail(movie: Movie): Fragment {
          val fragment = MovieFragment()
          val argument = Bundle()
          argument.putBoolean(ARGUMENT_FAVORITE, false)
          argument.putBoolean(ARGUMENT_SEARCH, false)
          argument.putParcelable("movie", movie)
          fragment.arguments = argument
          return fragment
      }
  }
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_movie, container, false)
  }

  private fun loadArgument() {
    if (arguments == null) return

    movieViewModel.favorite = arguments!!.getBoolean(ARGUMENT_FAVORITE, false)
      search = arguments!!.getBoolean(ARGUMENT_SEARCH, false)

      if (arguments!!.getParcelable<Movie>("movie") != null) {
          val movie: Movie? = arguments!!.getParcelable("movie")
          if (movie != null)
              startActivity(MovieDetailActivity.newIntent(context!!, movie))
      }
  }

  @SuppressLint("SetTextI18n")
  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    loadArgument()

      movieViewModel.start()

    va_data.displayedChild = 0

      adapter = PagedMovieAdapter()//MovieAdapter(context!!)

    rv_data.layoutManager = LinearLayoutManager(context!!)
    rv_data.setHasFixedSize(true)
    rv_data.adapter = adapter

//    movieViewModel.movies.observe(this,
//      Observer<MutableList<Movie>> {
//        if (!it.isNullOrEmpty()) {
//          adapter.movies = it
//          adapter.notifyDataSetChanged()
//        }
//      }
//    )

//    movieViewModel.status.observe(this, Observer<Const.DataModel.ErrorType> {
//      when (it) {
//        Const.DataModel.ErrorType.DATA_FOUND -> {
//          va_data.displayedChild = 1
//          movieViewModel.page++
//        }
//        Const.DataModel.ErrorType.DATA_NOT_FOUND -> {
//          va_data.displayedChild = 2
//          tv_message.text = getString(R.string.message_data_not_found)
//        }
//        else -> {
//          va_data.displayedChild = 2
//          tv_message.text = getString(R.string.message_error_universal)
//        }
//      }
//    })

      (if (search) movieSearchViewModel.movieList else movieViewModel.movieList)
          .observe(this, Observer<PagedList<Movie>> { items ->
              adapter.submitList(items)
              adapter.notifyDataSetChanged()
          })

      (if (search) movieSearchViewModel.movieState else movieViewModel.movieState)
          .observe(this, Observer<C.State> { state ->
              when (state) {
                  C.State.DONE -> {
          va_data.displayedChild = 1
        }
                  C.State.LOADING -> {
                      va_data.displayedChild = 0
        }
                  C.State.ERROR -> {
          va_data.displayedChild = 2
          tv_message.text = getString(R.string.message_error_universal)
        }

      }
    })

      swipe_refresh.setOnRefreshListener {
          if (search) movieSearchViewModel.refresh() else movieViewModel.refresh()
          swipe_refresh.isRefreshing = false
      }
  }



}