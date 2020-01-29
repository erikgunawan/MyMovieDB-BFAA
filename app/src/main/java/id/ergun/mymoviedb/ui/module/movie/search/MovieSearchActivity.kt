package id.ergun.mymoviedb.ui.module.movie.search

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuItemCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import id.ergun.mymoviedb.R
import id.ergun.mymoviedb.data.model.Movie
import id.ergun.mymoviedb.ui.module.movie.MovieAdapter
import id.ergun.mymoviedb.ui.module.utils.Const
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.android.synthetic.main.view_toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by alfacart on 26/12/19.
 */
class MovieSearchActivity : AppCompatActivity() {

    private val movieViewModel: MovieSearchViewModel by viewModel()

    lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setSupportActionBar(toolbar)
        supportActionBar?.run {
            setDisplayShowHomeEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }


//        loadArgument()

        va_data.displayedChild = 0

        adapter = MovieAdapter(this)

        rv_data.layoutManager = LinearLayoutManager(this)
        rv_data.setHasFixedSize(true)
        rv_data.adapter = adapter
//    setupScrollListener()

//    rv_data.addOnScrollListener()

        movieViewModel.movies.observe(this,
            Observer<MutableList<Movie>> {
                adapter.movies.clear()
                if (!it.isNullOrEmpty()) {
                    adapter.movies = it
                    adapter.notifyDataSetChanged()
                }
            }
        )

        movieViewModel.status.observe(this, Observer<Const.DataModel.ErrorType> {
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
                    Log.d("onquerytextsubmit", query.toString())
                    searchView.hideKeyboard()
                    if (query != null)
                        movieViewModel.searchMovie(query.toString())

                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    Log.d("onquerytextchange", newText.toString())
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