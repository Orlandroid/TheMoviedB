package com.example.themoviedb.presentacion.ui.jobs

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.themoviedb.R
import com.example.themoviedb.databinding.FragmentJobsBinding
import com.example.themoviedb.domain.state.Result
import com.example.themoviedb.presentacion.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JobsFragment : BaseFragment<FragmentJobsBinding>(R.layout.fragment_jobs) {

    private val viewModel: JobsViewModel by viewModels()
    private val jobsAdapter by lazy {
        JobsAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUi()
        observerViewModel()
    }

    @SuppressLint("SetTextI18n")
    override fun setUpUi() {
        viewModel.getJobs()
        with(binding) {
            toolbarLayout.toolbarBack.setOnClickListener {
                findNavController().popBackStack()
            }
            toolbarLayout.toolbarTitle.text = "Jobs"
            recyclerJobs.adapter = jobsAdapter
        }
    }

    override fun observerViewModel() {
        viewModel.jobsResponse.observe(viewLifecycleOwner) {
            when (it) {
                is Result.Loading -> {

                }
                is Result.Success -> {
                    jobsAdapter.setData(it.data)
                }
                is Result.EmptyList -> {

                }
                is Result.Error -> {

                }
                is Result.ErrorNetwork -> {

                }
            }
        }
    }

}