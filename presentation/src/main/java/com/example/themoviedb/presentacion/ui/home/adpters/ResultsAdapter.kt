package com.example.themoviedb.presentacion.ui.home.adpters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.domain.entities.remote.ResultMovie
import com.example.themoviedb.R
import com.example.themoviedb.databinding.ItemResultBinding
import com.example.themoviedb.presentacion.ui.extensions.click
import com.example.themoviedb.presentacion.util.ImageUtil
import com.example.themoviedb.presentacion.util.getAverageInCents

class ResultsAdapter(private val clickOnMovie: (result: ResultMovie) -> Unit = {}) :
    PagingDataAdapter<ResultMovie, ResultsAdapter.ResultViewHolder>(ResultAdapterComparator) {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ResultViewHolder {
        return ResultViewHolder(
            ItemResultBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(it)
        }
    }


    inner class ResultViewHolder(private val binding: ItemResultBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(result: ResultMovie) {
            with(binding) {
                val imageUtil = ImageUtil()
                title.text = result.title
                fecha.text = result.release_date
                progressBar.progress = getAverageInCents(result.vote_average)
                progressPorcent.text = "${getAverageInCents(result.vote_average)} %"
                result.backdrop_path?.let { backdropPath ->
                    Glide.with(itemView.context)
                        .load(imageUtil.getBaseUrlImagePoster(urlImage = backdropPath))
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .placeholder(R.drawable.loading_animation).into(imageResult)
                }
                root.click {
                    clickOnMovie(result)
                }
            }
        }
    }


    object ResultAdapterComparator : DiffUtil.ItemCallback<ResultMovie>() {
        override fun areItemsTheSame(oldItem: ResultMovie, newItem: ResultMovie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ResultMovie, newItem: ResultMovie): Boolean {
            return oldItem == newItem
        }
    }


}
