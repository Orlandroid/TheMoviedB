package com.example.themoviedb.presentacion.ui.jobs.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entities.remote.JobsResponse
import com.example.themoviedb.R
import com.example.themoviedb.databinding.ItemJobBinding

class JobsAdapter : RecyclerView.Adapter<JobsAdapter.ViewHolder>() {

    private var listOfJobs = listOf<String>()

    companion object {
        private var imageJob: Int? = null
    }

    fun setData(jobsResponse: JobsResponse) {
        //imageJob=jobsResponse.getImageDepartment()
        imageJob = R.drawable.actor
        listOfJobs = jobsResponse.jobs
        notifyDataSetChanged()
    }


    class ViewHolder(private val binding: ItemJobBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(job: String) {
            with(binding) {
                imageJob?.let { imageView2.setImageResource(it) }
                tvTitleJob.text = job
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