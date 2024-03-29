package id.ergun.mymoviedb.ui.module.main

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import id.ergun.mymoviedb.R
import id.ergun.mymoviedb.ui.module.favorite.FavoriteFragment
import id.ergun.mymoviedb.ui.module.movie.MovieFragment
import id.ergun.mymoviedb.ui.module.tv.TvShowFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_toolbar.*


/**
 * Created by erikgunawan on 27/11/19.
 */
class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    setSupportActionBar(toolbar)
    supportActionBar?.run {
      title = getString(R.string.app_name)
      elevation = 0F
    }

      loadFragment(MovieFragment())

      bnv_main.setOnNavigationItemSelectedListener(this)
  }

    private fun loadFragment(fragment: Fragment?): Boolean {
        if (fragment != null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.layout_container, fragment)
                .commit()
            return true
        }
        return false
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    menuInflater.inflate(R.menu.menu_setting, menu)
    return super.onCreateOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    if (item.itemId == R.id.action_change_settings) {
      val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
      startActivity(mIntent)
    }
    return super.onOptionsItemSelected(item)
  }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment: Fragment? = null
        when (item.itemId) {
            R.id.action_movies -> fragment = MovieFragment()
            R.id.action_tv_show -> fragment = TvShowFragment()
            R.id.action_favorite -> fragment = FavoriteFragment()
        }
        return loadFragment(fragment)
    }
}