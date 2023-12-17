package com.example.themoviedb.presentacion.ui.splashscreen

import android.annotation.SuppressLint
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.themoviedb.R
import com.example.themoviedb.databinding.FragmentSplashScreenBinding
import com.example.themoviedb.presentacion.base.BaseFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@SuppressLint("CustomSplashScreen")
class SplashScreenFragment :
    BaseFragment<FragmentSplashScreenBinding>(R.layout.fragment_splash_screen) {

    override fun setUpUi() {
        lifecycleScope.launch {
            delay(2000)
            findNavController().navigate(SplashScreenFragmentDirections.actionSplashScreenFragmentToHomeMoviesFragment())
        }
    }

}