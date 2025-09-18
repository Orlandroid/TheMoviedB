package com.example.themoviedb.presentacion.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.themoviedb.R
import com.example.themoviedb.databinding.ActivityMainBinding
import com.example.themoviedb.presentacion.helpers.gone
import com.example.themoviedb.presentacion.helpers.visible
import com.example.themoviedb.presentacion.ui.extensions.click
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpUi()
        setUpNavController()
    }

    private fun setUpNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }

    private fun setUpUi() {
        with(binding) {
//            setSupportActionBar(toolbarLayout.toolbar) // removed because error when compaling
            toolbarLayout.toolbarBack.gone()
        }
    }

    fun hydeToolbar() {
        binding.toolbarLayout.root.gone()
    }

    fun showToolbar(shouldShow: Boolean) {
        if (shouldShow) {
            binding.toolbarLayout.root.visible()
        } else {
            binding.toolbarLayout.root.gone()
        }
    }

    private fun showProgress() {
        binding.progressBar3.visible()
    }

    fun hideProgress() {
        binding.progressBar3.gone()
    }

    fun shouldShowProgress(isLoading: Boolean) {
        if (isLoading) {
            showProgress()
        } else {
            hideProgress()
        }
    }

    fun changeTextToolbar(title: String) {
        binding.toolbarLayout.toolbarTitle.text = title
    }

    private fun showBackArrow(shouldShow: Boolean) {
        if (shouldShow) {
            binding.toolbarLayout.toolbarBack.visible()
        } else {
            binding.toolbarLayout.toolbarBack.gone()
        }
    }

    private fun clickOnSettings(clickOnSettings: (() -> Unit)?) {
        binding.toolbarLayout.imageSettings.click {
            clickOnSettings?.invoke()
        }
    }

    private fun setOnBackButton(clickOnBack: (() -> Unit)?) = with(binding) {
        val clickOnBackButton = if (clickOnBack == null) {
            {
                navController?.popBackStack()
            }
        } else {
            {
                clickOnBack()
            }
        }
        toolbarLayout.toolbarBack.click {
            clickOnBackButton()
        }
    }

    fun setToolbarConfiguration(configuration: ToolbarConfiguration) {
        setOnBackButton(configuration.clickOnBack)
        changeTextToolbar(configuration.toolbarTitle)
        showToolbar(configuration.showToolbar)
        showBackArrow(configuration.showBackArrow)
        clickOnSettings(configuration.clickOnSettings)
    }

    data class ToolbarConfiguration(
        val showToolbar: Boolean = false,
        val showBackArrow: Boolean = true,
        val clickOnBack: (() -> Unit)? = null,
        val clickOnSettings: (() -> Unit)? = null,
        val toolbarTitle: String = ""
    )

}