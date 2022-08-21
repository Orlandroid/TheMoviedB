package com.example.themoviedb.presentacion.ui.home.adpters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviedb.databinding.ItemMenuBinding

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private var listOfMenu = listOf<Menu>()

    fun setData(posts: List<Menu>) {
        listOfMenu = posts
        notifyDataSetChanged()
    }


    class ViewHolder(private val binding: ItemMenuBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(menu: Menu) {
            with(binding) {
                txtNombreMenu.text = menu.title
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val binding = ItemMenuBinding.inflate(layoutInflater,viewGroup,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(listOfMenu[position])
    }

    override fun getItemCount() = listOfMenu.size


    data class Menu(
        val title: String,
        val image: Int
    )

}
