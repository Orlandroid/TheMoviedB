package com.example.themoviedb.presentacion.ui.home.popular

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.themoviedb.R
import com.example.themoviedb.databinding.FragmentPopularBinding
import com.example.themoviedb.domain.state.Result
import com.example.themoviedb.presentacion.base.BaseFragment
import com.example.themoviedb.presentacion.ui.home.adpters.ResultsAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PopularFragment : BaseFragment<FragmentPopularBinding>(R.layout.fragment_popular) {

    private val viewModel: PopularViewModel by viewModels()

    private val popularAdapter by lazy {
        ResultsAdapter()
    }

    override fun setUpUi() {
        viewModel.getPopularTv()
        with(binding) {
            recycler.adapter = popularAdapter
        }
    }

    override fun observerViewModel() {
        super.observerViewModel()
        viewModel.popularTvResponse.observe(viewLifecycleOwner) {
            binding.progressBar2.isVisible = it is Result.Loading
            when (it) {
                is Result.Success -> {
                    popularAdapter.setData(it.data.results)
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