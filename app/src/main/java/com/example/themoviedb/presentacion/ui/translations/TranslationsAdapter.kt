package com.example.themoviedb.presentacion.ui.translations

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviedb.R
import com.example.themoviedb.databinding.ItemJobBinding


class TranslationsAdapter : ListAdapter<String, TranslationsAdapter.ItemViewHolder>(DiffCallback) {


    class ItemViewHolder(private var binding: ItemJobBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(translation: String) {
            binding.apply {
                imageView2.setImageResource(R.drawable.languages)
                tvTitleJob.text = translation
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemJobBinding.inflate(layoutInflater, parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }


    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }
        }
    }
}
