package id.ergun.mymoviedb.ui.module.main

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import id.ergun.mymoviedb.R
import id.ergun.mymoviedb.ui.module.movie.MovieFragment
import id.ergun.mymoviedb.ui.module.tv.TvShowFragment
import kotlinx.android.synthetic.main.activity_main.tabLayout
import kotlinx.android.synthetic.main.activity_main.viewPager
import kotlinx.android.synthetic.main.view_toolbar.toolbar

/**
 * Created by alfacart on 27/11/19.
 */
class MainActivity : AppCompatActivity() {

  lateinit var adapter: ViewPagerAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    setSupportActionBar(toolbar)
    supportActionBar?.run {
      title = getString(R.string.app_name)
      elevation = 0F
    }

    val fragments = mutableListOf<Fragment>()
    fragments.add(MovieFragment())
    fragments.add(TvShowFragment())

    val titles = mutableListOf<String>()
    titles.add(getString(R.string.movies))
    titles.add(getString(R.string.tv_show))

    adapter = ViewPagerAdapter(supportFragmentManager)
    adapter.data = fragments
    adapter.pageTitle = titles

    viewPager.adapter = adapter
    tabLayout.setupWithViewPager(viewPager)
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
}