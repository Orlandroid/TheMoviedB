package com.example.themoviedb.presentacion.ui.home.adpters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.themoviedb.R
import com.example.themoviedb.databinding.ItemResultBinding
import com.example.themoviedb.domain.entities.remote.Result
import com.example.themoviedb.presentacion.ui.extensions.click
import com.example.themoviedb.presentacion.util.ImageUtil
import com.example.themoviedb.presentacion.util.getAverageInCents

class ResultsAdapter(private val clickOnMovie: (result: Result) -> Unit = {}) :
    RecyclerView.Adapter<ResultsAdapter.ViewHolder>() {

    private var listOfResults = listOf<Result>()

    fun setData(posts: List<Result>) {
        listOfResults = posts
        notifyDataSetChanged()
    }


    class ViewHolder(private val binding: ItemResultBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(result: Result) {
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
            }
        }
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val binding = ItemResultBinding.inflate(layoutInflater, viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = listOfResults[position]
        viewHolder.bind(item)
        viewHolder.itemView.click {
            clickOnMovie(item)
        }
    }

    override fun getItemCount() = listOfResults.size


}
