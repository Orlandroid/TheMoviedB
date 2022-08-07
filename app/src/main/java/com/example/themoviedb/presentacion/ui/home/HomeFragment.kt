package com.example.themoviedb.presentacion.ui.home


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.example.themoviedb.R
import com.example.themoviedb.databinding.FragmentHomeBinding
import com.example.themoviedb.presentacion.base.BaseFragment
import com.example.themoviedb.domain.state.Result
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUi()
        observerViewModel()
    }

    override fun setUpUi() {
        viewModel.getProviders()
        with(binding) {

        }
    }

    override fun observerViewModel() {
        viewModel.providers.observe(viewLifecycleOwner) {
            when (it) {
                is Result.Loading -> {
                    Log.i("DEBUG","Loading")
                }
                is Result.Success -> {
                    Log.i("DEBUG","Succes")
                }
                is Result.EmptyList -> {
                    Log.i("DEBUG","EmptyList")
                }
                is Result.Error -> {
                    Log.i("DEBUG",it.error)
                }
                is Result.ErrorNetwork -> {
                    Log.i("DEBUG","ErrorNetwork")
                }
            }
        }
    }

}