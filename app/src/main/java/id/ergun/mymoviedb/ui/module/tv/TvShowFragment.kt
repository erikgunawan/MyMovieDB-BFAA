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
import id.ergun.mymoviedb.ui.module.utils.Const
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.android.synthetic.main.fragment_tv_show.rv_data
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by erikgunawan on 27/11/19.
 */
class TvShowFragment : Fragment() {

    private val tvViewModel: TvShowViewModel by viewModel()

    lateinit var adapter: TvShowAdapter

    companion object {

        private const val ARGUMENT_FAVORITE = "ARGUMENT_FAVORITE"

        fun newInstance(favorite: Boolean = false): Fragment {
            val fragment = TvShowFragment()
            val argument = Bundle()
            argument.putBoolean(ARGUMENT_FAVORITE, favorite)
            fragment.arguments = argument
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    private fun loadArgument() {
        if (arguments == null) return

        tvViewModel.favorite = arguments!!.getBoolean(ARGUMENT_FAVORITE, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loadArgument()

        va_data.displayedChild = 0

        adapter = TvShowAdapter(context!!)

        val rvData = rv_data
        rvData.layoutManager = LinearLayoutManager(context!!)
        rvData.setHasFixedSize(true)
        rvData.adapter = adapter

        tvViewModel.tvShows.observe(this,
            Observer<MutableList<Tv>> {
                if (!it.isNullOrEmpty()) {
                    adapter.tvShows = it
                    adapter.notifyDataSetChanged()
                }
            }
        )

        tvViewModel.status.observe(this, Observer<Const.DataModel.ErrorType> {
            when (it) {
                Const.DataModel.ErrorType.DATA_FOUND -> {
                    va_data.displayedChild = 1
                }
                Const.DataModel.ErrorType.DATA_NOT_FOUND -> {
                    va_data.displayedChild = 2
                    tv_message.text = getString(R.string.message_data_not_found)
                }
                else -> {
                    va_data.displayedChild = 2
                    tv_message.text = getString(R.string.message_error_universal)
                }
            }
        })
        tvViewModel.loadTvShows()
    }

    override fun onResume() {
        super.onResume()
        tvViewModel.loadTvShows()
    }
}