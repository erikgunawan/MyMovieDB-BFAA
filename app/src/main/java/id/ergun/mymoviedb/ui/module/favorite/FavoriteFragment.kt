package id.ergun.mymoviedb.ui.module.favorite

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import id.ergun.mymoviedb.R
import id.ergun.mymoviedb.ui.module.main.ViewPagerAdapter
import id.ergun.mymoviedb.ui.module.movie.MovieFragment
import id.ergun.mymoviedb.ui.module.tv.TvShowFragment
import kotlinx.android.synthetic.main.fragment_favorite.*

/**
 * Created by erikgunawan on 04/12/19.
 */

class FavoriteFragment : Fragment() {

    lateinit var adapter: ViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val fragments = mutableListOf<Fragment>()
        fragments.add(MovieFragment.newInstance(true))
        fragments.add(TvShowFragment.newInstance(true))

        val titles = mutableListOf<String>()
        titles.add(getString(R.string.movies))
        titles.add(getString(R.string.tv_show))

        adapter = ViewPagerAdapter(childFragmentManager)
        adapter.data = fragments
        adapter.pageTitle = titles

        view_pager.adapter = adapter
        tab_layout.setupWithViewPager(view_pager)
    }

}