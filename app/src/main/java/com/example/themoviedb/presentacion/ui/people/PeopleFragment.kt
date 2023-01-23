package com.example.themoviedb.presentacion.ui.people

import androidx.fragment.app.viewModels
import com.example.themoviedb.R
import com.example.themoviedb.databinding.FragmentPeopleBinding
import com.example.themoviedb.presentacion.base.BaseFragment
import com.example.themoviedb.presentacion.ui.extensions.observeApiResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PeopleFragment : BaseFragment<FragmentPeopleBinding>(R.layout.fragment_people) {

    private val viewModel: PeopleViewModel by viewModels()

    override fun setUpUi() {
        with(binding) {
            viewModel.getPeople()
        }
    }

    override fun observerViewModel() {
        super.observerViewModel()
        observeApiResult(viewModel.peoplePopularResponse)
    }

}