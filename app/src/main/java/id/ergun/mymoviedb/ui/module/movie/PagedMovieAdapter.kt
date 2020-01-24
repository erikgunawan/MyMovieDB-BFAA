package id.ergun.mymoviedb.ui.module.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.ergun.mymoviedb.BuildConfig.IMAGE_URL
import id.ergun.mymoviedb.R
import id.ergun.mymoviedb.data.model.Movie
import id.ergun.mymoviedb.databinding.ItemMovieBinding
import kotlinx.android.synthetic.main.item_movie.view.*

/**
 * Created by alfacart on 30/12/19.
 */

class PagedMovieAdapter : PagedListAdapter<Movie, RecyclerView.ViewHolder>(diffCallback) {

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.equals(newItem)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        (holder as MovieViewHolder).bind(getItem(position))
//        val movie = movies[position]
//        holder.tvTitle.text = movie.title
//        holder.tvOverview.text = movie.overview
//
//        Glide.with(holder.itemView.context)
//            .load(BuildConfig.IMAGE_URL + movie.posterPath)
//            .apply(RequestOptions.centerInsideTransform())
//            .into(holder.ivPoster)
//
//        holder.btnDetail.setOnClickListener {
//            when (context) {
//                is MainActivity -> context.startActivity(
//                    MovieDetailActivity.newIntent(
//                        context,
//                        movie
//                    )
//                )
//            }
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MovieViewHolder.create(parent)
    }

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(movie: Movie?) {
            if (movie == null) return

            itemView.tv_title.text = movie.title
            itemView.tv_overview.text = movie.overview

            Glide.with(itemView.context)
                .load(IMAGE_URL + movie.posterPath)
                .apply(RequestOptions.centerInsideTransform())
                .into(itemView.iv_image)

//        holder.btnDetail.setOnClickListener {
//            when (context) {
//                is MainActivity -> context.startActivity(
//                    MovieDetailActivity.newIntent(
//                        context,
//                        movie
//                    )
//                )
//            }
//        }
        }

        companion object {
            fun create(parent: ViewGroup): MovieViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_movie, parent, false)
                return MovieViewHolder(view)
            }
        }
    }

    inner class ViewHolder(binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvTitle = binding.tvTitle!!
        val tvOverview = binding.tvOverview!!
        val ivPoster = binding.ivImage!!
        val btnDetail = binding.viewItem!!
    }

}