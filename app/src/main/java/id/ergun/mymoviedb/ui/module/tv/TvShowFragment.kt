package id.ergun.mymoviedb.ui.module.tv

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import id.ergun.mymoviedb.R
import id.ergun.mymoviedb.data.model.Tv
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.android.synthetic.main.fragment_tv_show.rv_data
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by erikgunawan on 27/11/19.
 */
class TvShowFragment : Fragment() {

  private val movieViewModel: TvShowViewModel by viewModel()

  lateinit var adapter: TvShowAdapter

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_tv_show, container, false)
  }

  @SuppressLint("SetTextI18n")
  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)

    va_data.displayedChild = 0

    adapter = TvShowAdapter(context!!)

    val rvData = rv_data
    rvData.layoutManager = LinearLayoutManager(context!!)
    rvData.setHasFixedSize(true)
    rvData.adapter = adapter

    movieViewModel.movies.observe(this,
      Observer<MutableList<Tv>> {
        if (it != null) {
          adapter.tvShows = it
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