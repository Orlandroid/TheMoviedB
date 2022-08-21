package com.example.themoviedb.presentacion.ui.dialogs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviedb.databinding.AlertDialogItemOptionBinding

class DialogAdapter : RecyclerView.Adapter<DialogAdapter.ViewHolder>() {

    private var listOfOptions = listOf<String>()
    private var listener: ClickOnOption?=null

    fun setListener(listener: ClickOnOption) {
        this.listener = listener
    }

    fun setData(posts: List<String>) {
        listOfOptions = posts
        notifyDataSetChanged()
    }


    class ViewHolder(private val binding: AlertDialogItemOptionBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(option: String) {
            with(binding) {
                headerName.text = option
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val binding = AlertDialogItemOptionBinding.inflate(layoutInflater,viewGroup,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(listOfOptions[position])
        viewHolder.itemView.setOnClickListener {
            listener?.clickOnOption(position)
        }
    }

    override fun getItemCount() = listOfOptions.size

    interface ClickOnOption {
        fun clickOnOption(position: Int)
    }
}
