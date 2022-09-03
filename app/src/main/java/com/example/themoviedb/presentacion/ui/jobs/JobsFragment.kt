package com.example.themoviedb.presentacion.ui.jobs

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.themoviedb.R
import com.example.themoviedb.databinding.FragmentJobsBinding
import com.example.themoviedb.presentacion.base.BaseFragment
import com.example.themoviedb.presentacion.ui.MainActivity
import com.example.themoviedb.presentacion.ui.jobs.adapter.JobsAdapter


class JobsFragment : BaseFragment<FragmentJobsBinding>(R.layout.fragment_jobs) {

    private val jobsAdapter by lazy {
        JobsAdapter()
    }
    private val args: JobsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUi()
    }


    override fun setUpUi() {
        (requireActivity() as MainActivity).hydeToolbar()
        with(binding) {
            recyclerView.adapter = jobsAdapter
            jobsAdapter.setData(args.jobResponse)
            toolbarLayout.toolbarBack.setOnClickListener {
                findNavController().popBackStack()
            }
            toolbarLayout.toolbarTitle.text = "Jobs"
        }
    }

}