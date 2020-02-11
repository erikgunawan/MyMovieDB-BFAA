package id.ergun.mymoviedb.ui.module.search

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.Fragment
import id.ergun.mymoviedb.R
import id.ergun.mymoviedb.ui.module.main.ViewPagerAdapter
import id.ergun.mymoviedb.ui.module.movie.MovieFragment
import id.ergun.mymoviedb.ui.module.movie.search.MovieSearchViewModel
import id.ergun.mymoviedb.ui.module.tv.TvShowFragment
import id.ergun.mymoviedb.ui.module.tv.search.TvShowSearchViewModel
import kotlinx.android.synthetic.main.fragment_favorite.*
import kotlinx.android.synthetic.main.view_toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by alfacart on 26/12/19.
 */
class SearchActivity : AppCompatActivity() {

    private val movieViewModel: SearchViewModel by viewModel()

    private val movieSearchViewModel: MovieSearchViewModel by viewModel()
    private val tvSearchViewModel: TvShowSearchViewModel by viewModel()

    lateinit var adapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setSupportActionBar(toolbar)
        supportActionBar?.run {
            setDisplayShowHomeEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }


        val fragments = mutableListOf<Fragment>()
        fragments.add(MovieFragment.newInstance(search = true))
        fragments.add(TvShowFragment.newInstance(search = true))

        val titles = mutableListOf<String>()
        titles.add(getString(R.string.movies))
        titles.add(getString(R.string.tv_show))

        adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.data = fragments
        adapter.pageTitle = titles

        view_pager.adapter = adapter
        tab_layout.setupWithViewPager(view_pager)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
//         Retrieve the SearchView and plug it into SearchManager
        val search = menu!!.findItem(R.id.action_search)
//      val searchView: SearchView = search.actionView as SearchView

        val searchView = MenuItemCompat.getActionView(search) as SearchView

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.run {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            onActionViewExpanded()
            imeOptions = EditorInfo.IME_ACTION_DONE
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    searchView.setQuery(query, false)
                    searchView.hideKeyboard()
                    if (query != null) {
                        movieSearchViewModel.refresh()
                        movieSearchViewModel.newSearch(query.toString())

                        tvSearchViewModel.refresh()
                        tvSearchViewModel.newSearch(query.toString())
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return true
                }
            })

            return super.onCreateOptionsMenu(menu)
        }
    }

    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}