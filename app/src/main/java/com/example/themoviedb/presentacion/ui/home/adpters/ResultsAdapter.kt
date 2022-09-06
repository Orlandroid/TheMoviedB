package com.example.themoviedb.presentacion.ui.home.adpters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviedb.databinding.ItemResultBinding
import com.example.themoviedb.domain.entities.remote.Result
import com.example.themoviedb.presentacion.helpers.loadImage

class ResultsAdapter : RecyclerView.Adapter<ResultsAdapter.ViewHolder>() {

    private var listOfResults = listOf<Result>()

    fun setData(posts: List<Result>) {
        listOfResults = posts
        notifyDataSetChanged()
    }


    class ViewHolder(private val binding: ItemResultBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(result: Result) {
            with(binding) {
                imageResult.loadImage(result.backdrop_path)
                title.text=result.title
                fecha.text=result.release_date
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val binding = ItemResultBinding.inflate(layoutInflater, viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(listOfResults[position])
    }

    override fun getItemCount() = listOfResults.size



}
