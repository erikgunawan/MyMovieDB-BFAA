package id.ergun.mymoviedb.ui.module.movie

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.ergun.mymoviedb.BuildConfig
import id.ergun.mymoviedb.R
import id.ergun.mymoviedb.data.model.Movie
import id.ergun.mymoviedb.databinding.ItemMovieBinding
import id.ergun.mymoviedb.ui.module.main.MainActivity
import id.ergun.mymoviedb.ui.module.movie.detail.MovieDetailActivity

/**
 * Created by erikgunawan on 25/11/19.
 */
class MovieAdapter(private val context: Context) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    var movies: MutableList<Movie> = mutableListOf()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.tvTitle.text = movie.title
        holder.tvOverview.text = movie.overview

        Glide.with(holder.itemView.context)
            .load(BuildConfig.IMAGE_URL + movie.posterPath)
            .apply(RequestOptions.centerInsideTransform())
            .into(holder.ivPoster)

        holder.btnDetail.setOnClickListener {
            when (context) {
                is MainActivity -> context.startActivity(
                    MovieDetailActivity.newIntent(
                        context,
                        movie
                    )
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemMovieBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_movie, parent, false
        )

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    inner class ViewHolder(binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvTitle = binding.tvTitle!!
        val tvOverview = binding.tvOverview!!
        val ivPoster = binding.ivImage!!
        val btnDetail = binding.viewItem!!
    }

}