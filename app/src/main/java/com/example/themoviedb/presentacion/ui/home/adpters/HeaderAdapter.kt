package com.example.themoviedb.ui.home.adpters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviedb.databinding.HeaderItemBinding


class HeaderAdapter : RecyclerView.Adapter<HeaderAdapter.ViewHolder>() {

    private var lisofHeaders = listOf<Header>()
    private lateinit var listener: ClickOnHeader

    fun setData(headers: List<Header>) {
        lisofHeaders = headers
        notifyDataSetChanged()
    }

    fun setListener(listener: ClickOnHeader) {
        this.listener = listener
    }

    class ViewHolder(private val binding: HeaderItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(header: Header) {
            with(binding) {
                headerName.text = header.nombre
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val binding = HeaderItemBinding.inflate(layoutInflater,viewGroup,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(lisofHeaders[position])
        viewHolder.itemView.setOnClickListener {
            when (position) {
                1 -> {
                    listener.clickOnMovie()
                }
                2 -> {
                    listener.clickOnSerie()
                }
                3 -> {
                    listener.clickOnPeople()
                }
            }
        }
    }

    override fun getItemCount() = lisofHeaders.size

    data class Header(
        val nombre: String,
        val background: Int
    )

    interface ClickOnHeader {
        fun clickOnMovie()
        fun clickOnSerie()
        fun clickOnPeople()
    }

}
