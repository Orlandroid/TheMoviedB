package com.example.themoviedb.presentacion.ui.jobs.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviedb.databinding.ItemJobBinding
import com.example.themoviedb.domain.entities.remote.JobsResponse

class DepartmentAdapter(private val clickOnAppartment: (JobsResponse) -> Unit) :
    RecyclerView.Adapter<DepartmentAdapter.ViewHolder>() {

    private var listOfAppartments = listOf<JobsResponse>()

    fun setData(apartments: List<JobsResponse>) {
        listOfAppartments = apartments
        notifyDataSetChanged()
    }


    class ViewHolder(private val binding: ItemJobBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(job: JobsResponse) {
            with(binding) {
                imageView2.setImageResource(job.getImageDepartment())
                tvTitleJob.text = job.department
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val binding = ItemJobBinding.inflate(layoutInflater, viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(listOfAppartments[position])
        viewHolder.itemView.setOnClickListener {
            clickOnAppartment(listOfAppartments[position])
        }
    }

    override fun getItemCount() = listOfAppartments.size


}