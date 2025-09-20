package com.example.themoviedb.presentacion.ui.settings

import androidx.compose.material3.Text
import com.example.themoviedb.R
import com.example.themoviedb.databinding.FragmentSettingsBinding
import com.example.themoviedb.presentacion.base.BaseFragment
import com.example.themoviedb.presentacion.ui.MainActivity


class SettingsFragment : BaseFragment<FragmentSettingsBinding>(R.layout.fragment_settings) {

    override fun configureToolbar() =
        MainActivity.ToolbarConfiguration(
            showToolbar = true,
            toolbarTitle = getString(R.string.settings)
        )

    override fun setUpUi() {
        binding.composeView.setContent {
            Text("Welcome to compose")
        }
    }

}