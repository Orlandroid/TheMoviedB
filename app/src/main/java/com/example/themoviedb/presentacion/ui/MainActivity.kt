package com.example.themoviedb.presentacion.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.themoviedb.R
import com.example.themoviedb.databinding.ActivityMainBinding
import com.example.themoviedb.presentacion.helpers.gone
import com.example.themoviedb.presentacion.helpers.visible
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpUi()
        setUpDrawer()
    }

    private fun setUpUi() {
        with(binding) {
            setSupportActionBar(toolbarLayout.toolbar)
            toolbarLayout.root.visible()
            toolbarLayout.toolbarBack.gone()
        }
    }

    fun hydeToolbar() {
        binding.toolbarLayout.root.gone()
    }

    fun showToolbar() {
        binding.toolbarLayout.root.visible()
    }

    fun showProgress() {
        binding.progressBar3.visible()
    }

    fun hideProgress() {
        binding.progressBar3.gone()
    }

    fun changeTextToolbar(title: String) {
        binding.toolbarLayout.toolbarTitle.text = title
    }

    private fun setUpDrawer() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph, binding.drawerLayout)
        binding.navView.setupWithNavController(navController)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) ||
                return super.onSupportNavigateUp()
    }

}