package id.ergun.mymoviedb.ui.module.tv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.ergun.mymoviedb.BuildConfig
import id.ergun.mymoviedb.R
import id.ergun.mymoviedb.data.model.Tv
import kotlinx.android.synthetic.main.item_movie.view.*

/**
 * Created by alfacart on 03/02/20.
 */

class PageTvShowAdapter : PagedListAdapter<Tv, RecyclerView.ViewHolder>(diffCallback) {

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Tv>() {
            override fun areItemsTheSame(oldItem: Tv, newItem: Tv): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Tv, newItem: Tv): Boolean {
                return oldItem.equals(newItem)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        (holder as TvViewHolder).bind(getItem(position))
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

        return TvViewHolder.create(parent)
    }

    class TvViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(tv: Tv?) {
            if (tv == null) return

            itemView.tv_title.text = tv.name
            itemView.tv_overview.text = tv.overview

            Glide.with(itemView.context)
                .load(BuildConfig.IMAGE_URL + tv.posterPath)
                .apply(RequestOptions.centerInsideTransform())
                .into(itemView.iv_image)
        }

        companion object {
            fun create(parent: ViewGroup): TvViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_movie, parent, false)
                return TvViewHolder(view)
            }
        }
    }
}