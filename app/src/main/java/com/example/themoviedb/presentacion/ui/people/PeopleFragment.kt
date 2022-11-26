package com.example.themoviedb.presentacion.ui.people

import android.os.Bundle
import android.view.View
import com.example.themoviedb.R
import com.example.themoviedb.databinding.FragmentPeopleBinding
import com.example.themoviedb.presentacion.base.BaseFragment


class PeopleFragment : BaseFragment<FragmentPeopleBinding>(R.layout.fragment_people) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUi()
    }

    override fun setUpUi() {

    }


}