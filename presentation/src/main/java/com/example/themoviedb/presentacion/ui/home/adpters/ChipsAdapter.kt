package com.example.themoviedb.presentacion.ui.home.adpters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviedb.databinding.ItemChipBinding

class ChipsAdapter : RecyclerView.Adapter<ChipsAdapter.ViewHolder>() {

    private var listOfMenu = listOf<Element>()

    fun setData(posts: List<Element>) {
        listOfMenu = posts
        notifyDataSetChanged()
    }


    class ViewHolder(private val binding: ItemChipBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(element: Element) {

        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val binding = ItemChipBinding.inflate(layoutInflater, viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(listOfMenu[position])
        viewHolder.itemView.setOnClickListener {
            listOfMenu[position].isCheck = listOfMenu[position].isCheck
            setData(listOfMenu)
        }
    }

    override fun getItemCount() = listOfMenu.size


    data class Element(
        val message: String,
        val textColor: Int = 0,
        val background: Int = 0,
        var isCheck: Boolean = false
    )

}
