package id.ergun.mymoviedb.ui.module.tv.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.ergun.mymoviedb.R
import id.ergun.mymoviedb.data.model.Tv
import id.ergun.mymoviedb.databinding.ActivityTvShowDetailBinding
import kotlinx.android.synthetic.main.view_toolbar.toolbar
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by alfacart on 27/11/19.
 */
class TvShowDetailActivity : AppCompatActivity() {

  private lateinit var binding: ActivityTvShowDetailBinding

  private val tvViewModel: TvShowDetailViewModel by viewModel()

  companion object {
    const val EXTRA_TV_SHOW = "EXTRA_TV_SHOW"
    fun newIntent(
      context: Context,
      tv: Tv
    ): Intent {
      val intent = Intent(context, TvShowDetailActivity::class.java)
      intent.putExtra(EXTRA_TV_SHOW, tv)
      return intent
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_tv_show_detail)

    setSupportActionBar(toolbar)
    supportActionBar?.run {
      setDisplayShowHomeEnabled(true)
      setDisplayHomeAsUpEnabled(true)
    }

    val tvShow = intent.getParcelableExtra<Tv>(EXTRA_TV_SHOW)

    tvViewModel.tvShow.observe(this,
        Observer<Tv> {
          if (it != null) {
            binding.tvTitle.text = getString(it.name)

            Glide.with(this)
                .load(it.image)
                .apply(RequestOptions.centerInsideTransform())
                .into(binding.ivPoster)

            binding.tvOverview.text = getString(it.overview)
          } else {
            Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT)
                .show()
          }
        }
    )
    tvViewModel.getData(tvShow!!)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      android.R.id.home -> finish()
      R.id.action_change_settings -> {
        val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
        startActivity(mIntent)
      }
    }
    return super.onOptionsItemSelected(item)
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    menuInflater.inflate(R.menu.menu_setting, menu)
    return super.onCreateOptionsMenu(menu)
  }

}