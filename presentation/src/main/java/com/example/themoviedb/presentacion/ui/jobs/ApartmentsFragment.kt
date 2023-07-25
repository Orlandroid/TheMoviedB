package com.example.themoviedb.presentacion.ui.jobs

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.themoviedb.R
import com.example.themoviedb.databinding.FragmentApartmentsBinding
import com.example.domain.state.Result
import com.example.themoviedb.presentacion.base.BaseFragment
import com.example.themoviedb.presentacion.ui.MainActivity
import com.example.themoviedb.presentacion.ui.jobs.adapter.DepartmentAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ApartmentsFragment : BaseFragment<FragmentApartmentsBinding>(R.layout.fragment_apartments) {

    private val viewModel: JobsViewModel by viewModels()
    private lateinit var jobsAdapter: DepartmentAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUi()
        observerViewModel()
    }

    @SuppressLint("SetTextI18n")
    override fun setUpUi() {
        (requireActivity() as MainActivity).hydeToolbar()
        viewModel.getJobs()
        with(binding) {
            toolbarLayout.toolbarBack.setOnClickListener {
                findNavController().popBackStack()
            }
            toolbarLayout.toolbarTitle.text = "Apartments"
            jobsAdapter = DepartmentAdapter {
                val action = ApartmentsFragmentDirections.actionAppartmentJobsToJobsFragment2(it)
                findNavController().navigate(action)
            }
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