package com.example.themoviedb.presentacion.ui.translations

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.themoviedb.R
import com.example.themoviedb.databinding.FragmentTranslationsBinding
import com.example.themoviedb.presentacion.base.BaseFragment
import com.example.themoviedb.presentacion.ui.MainActivity
import com.example.themoviedb.presentacion.ui.dialogs.DialogInfo
import com.example.themoviedb.presentacion.ui.dialogs.DialogInfo.Companion.ERROR_MESSAGE
import com.example.domain.state.Result
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TranslationsFragment :
    BaseFragment<FragmentTranslationsBinding>(R.layout.fragment_translations) {

    private val viewModel: TranslationsViewModel by viewModels()
    private val translationsAdapter by lazy {
        TranslationsAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUi()
        observerViewModel()
    }

    @SuppressLint("SetTextI18n")
    override fun setUpUi() {
        (requireActivity() as MainActivity).hydeToolbar()
        viewModel.getTranslations()
        with(binding) {
            toolbarLayout.toolbarBack.setOnClickListener {
                findNavController().popBackStack()
            }
            toolbarLayout.toolbarTitle.text = "Translations"
            recyclerTranslations.adapter = translationsAdapter
        }
    }

    override fun observerViewModel() {
        viewModel.translationResponse.observe(viewLifecycleOwner) {
            binding.progressBar.isVisible = it is Result.Loading
            when (it) {
                is Result.Success -> {
                    translationsAdapter.submitList(it.data)
                }

                is Result.EmptyList -> {

                }

                is Result.Error -> {
                    val alert = DialogInfo(kindOfMessage = ERROR_MESSAGE, messageBody = it.error)
                    activity?.supportFragmentManager?.let { it1 -> alert.show(it1, "ALERT_INFO") }
                }

                is Result.ErrorNetwork -> {
                    val alert = DialogInfo(kindOfMessage = ERROR_MESSAGE, messageBody = it.error)
                    activity?.supportFragmentManager?.let { it1 -> alert.show(it1, "ALERT_INFO") }
                }

                else -> {}
            }
        }
        viewModel.languagesResponse.observe(viewLifecycleOwner) {
            binding.progressBar.isVisible = it is Result.Loading
            when (it) {
                is Result.Success -> {
                    //translationsAdapter.submitList(it.data)
                }

                is Result.EmptyList -> {

                }

                is Result.Error -> {
                    val alert = DialogInfo(kindOfMessage = ERROR_MESSAGE, messageBody = it.error)
                    activity?.supportFragmentManager?.let { it1 -> alert.show(it1, "ALERT_INFO") }
                }

                is Result.ErrorNetwork -> {
                    val alert = DialogInfo(kindOfMessage = ERROR_MESSAGE, messageBody = it.error)
                    activity?.supportFragmentManager?.let { it1 -> alert.show(it1, "ALERT_INFO") }
                }

                else -> {}
            }
        }
        super.observerViewModel()
    }

}