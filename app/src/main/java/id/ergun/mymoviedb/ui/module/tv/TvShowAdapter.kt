package id.ergun.mymoviedb.ui.module.tv

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.ergun.mymoviedb.R
import id.ergun.mymoviedb.data.model.Tv
import id.ergun.mymoviedb.databinding.ItemMovieBinding
import id.ergun.mymoviedb.ui.module.main.MainActivity
import id.ergun.mymoviedb.ui.module.movie.MovieActivity
import id.ergun.mymoviedb.ui.module.tv.detail.TvShowDetailActivity

/**
 * Created by erikgunawan on 27/11/19.
 */
class TvShowAdapter(private val context: Context) : RecyclerView.Adapter<TvShowAdapter.ViewHolder>() {

    var tvShows: MutableList<Tv> = mutableListOf()

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val tvShow = tvShows[position]
        holder.tvTitle.text = tvShow.name

        holder.tvOverview.text = tvShow.overview

        Glide.with(holder.itemView.context)
            .load(tvShow.posterPath)
            .apply(RequestOptions.centerInsideTransform())
            .into(holder.ivPoster)

        holder.btnDetail.setOnClickListener {
            when (context) {
                is MainActivity,
                is MovieActivity -> context.startActivity(
                    TvShowDetailActivity.newIntent(
                        context,
                        tvShow
                    )
                )
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding: ItemMovieBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_movie, parent, false
        )

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return tvShows.size
    }

    inner class ViewHolder(binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvTitle = binding.tvTitle!!
        val tvOverview = binding.tvOverview!!
        val ivPoster = binding.ivImage!!
        val btnDetail = binding.viewItem!!
    }

}