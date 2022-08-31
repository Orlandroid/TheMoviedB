package com.example.themoviedb.presentacion.ui.jobs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviedb.databinding.ItemJobBinding
import com.example.themoviedb.domain.entities.JobsResponse

class JobsAdapter : RecyclerView.Adapter<JobsAdapter.ViewHolder>() {

    private var listOfJobs = listOf<JobsResponse>()

    fun setData(jobs: List<JobsResponse>) {
        listOfJobs = jobs
        notifyDataSetChanged()
    }


    class ViewHolder(private val binding: ItemJobBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(job: JobsResponse) {
            with(binding) {
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
        viewHolder.bind(listOfJobs[position])
    }

    override fun getItemCount() = listOfJobs.size


}