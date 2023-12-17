package com.example.themoviedb.presentacion.ui.home.home


import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import com.example.themoviedb.R
import com.example.themoviedb.databinding.FragmentHomeBinding
import com.example.domain.state.Result
import com.example.themoviedb.presentacion.base.BaseFragment
import com.example.themoviedb.presentacion.ui.MainActivity
import com.example.themoviedb.presentacion.ui.dialogs.Dialogos
import com.example.themoviedb.presentacion.ui.home.adpters.ChipsAdapter
import com.example.themoviedb.presentacion.ui.home.adpters.HomeAdapter
import com.example.themoviedb.presentacion.ui.home.adpters.ResultsAdapter
import com.example.themoviedb.presentacion.ui.home.adpters.HeaderAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()
    private val homeAdapter by lazy {
        HomeAdapter()
    }
    private val headerAdapter by lazy {
        HeaderAdapter()
    }
    private val loMasPopularChipsAdapter by lazy {
        ChipsAdapter()
    }
    private val verGratisChipsAdapter by lazy {
        ChipsAdapter()
    }
    private val ultimosAvancesChipsAdapter by lazy {
        ChipsAdapter()
    }
    private val tendenciasChipsAdapter by lazy {
        ChipsAdapter()
    }
    private val resultsAdapter by lazy {
        ResultsAdapter()
    }

    private fun setItemsLoMasPolular() {
        val streaming = ChipsAdapter.Element("En streaming")
        val television = ChipsAdapter.Element("En Televisión ")
        val alquiler = ChipsAdapter.Element("En alquiler ")
        val cines = ChipsAdapter.Element("En cines ")
        loMasPopularChipsAdapter.setData(listOf(streaming, television, alquiler, cines))
    }

    private fun setItemsVerGratis() {
        val streaming = ChipsAdapter.Element("Películas")
        val television = ChipsAdapter.Element("Televisión ")
        verGratisChipsAdapter.setData(listOf(streaming, television))
    }

    private fun setItemsUltimosAvances() {
        val streaming = ChipsAdapter.Element("En streaming")
        val television = ChipsAdapter.Element("En televisión")
        val alquiler = ChipsAdapter.Element("En alquiler")
        val cines = ChipsAdapter.Element("En cines")
        ultimosAvancesChipsAdapter.setData(listOf(streaming, television, alquiler, cines))
    }

    private fun setItemsTendencias() {
        val hoy = ChipsAdapter.Element("Hoy")
        val estaSemana = ChipsAdapter.Element("Esta semana")
        tendenciasChipsAdapter.setData(listOf(hoy, estaSemana))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUi()
        observerViewModel()
    }

    override fun setUpUi() {
        (requireActivity() as MainActivity).showToolbar(true)
        //viewModel.getProviders()
        viewModel.getPopularTv()
        with(binding) {
            recyclerHome.adapter = homeAdapter
            recyclerHome.layoutManager = GridLayoutManager(requireContext(), 2)
            recyclerHeader.adapter = headerAdapter
            recyclerLoMasPopularChips.adapter = loMasPopularChipsAdapter
            recyclerLoMasPopular.adapter = resultsAdapter
            recyclerChipsVerGratis.adapter = verGratisChipsAdapter
            recyclerVerGratis.adapter = resultsAdapter
            recyclerChipsUltimosAvances.adapter = ultimosAvancesChipsAdapter
            recyclerUltimosAvances.adapter = resultsAdapter
            recyclerChipsTendencias.adapter = tendenciasChipsAdapter
            recyclerTendencias.adapter = resultsAdapter
            headerAdapter.setListener(object : HeaderAdapter.ClickOnHeader {
                override fun clickOnMovie() {
                    val dialogos = Dialogos()
                    val opciones = arrayOf("RED", "GREEN", "YELLOW", "BLACK", "MAGENTA", "PINK")
                    dialogos.showDialogMultiOption(context = requireContext(), opciones = opciones)
                }

                override fun clickOnSerie() {

                }

                override fun clickOnPeople() {

                }

            })
            setMenu()
            setHeaders()
            setItemsLoMasPolular()
            setItemsVerGratis()
            setItemsUltimosAvances()
            setItemsTendencias()
        }
    }

    override fun observerViewModel() {
        viewModel.providers.observe(viewLifecycleOwner) {
            when (it) {
                is Result.Loading -> {

                }

                is Result.Success -> {
                }

                is Result.EmptyList -> {

                }

                is Result.Error -> {

                }

                is Result.ErrorNetwork -> {

                }
            }
        }
        viewModel.popularTvResponse.observe(viewLifecycleOwner) {
            when (it) {
                is Result.Loading -> {

                }

                is Result.Success -> {
                    lifecycleScope.launch {
                        resultsAdapter.submitData(PagingData.from(it.data.results))
                    }
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

    private fun setMenu() {
        val galery = HomeAdapter.Menu("Provider", R.drawable.ic_launcher_foreground)
        val posts = HomeAdapter.Menu("Popular", R.drawable.ic_launcher_foreground)
        val menus = listOf(galery, posts)
        homeAdapter.setData(menus)
    }

    private fun setHeaders() {
        val peliculas = HeaderAdapter.Header("Peliculas", R.color.danger)
        val series = HeaderAdapter.Header("Series de TV", R.color.primary)
        val personas = HeaderAdapter.Header("Personas", R.color.primary)
        headerAdapter.setData(listOf(peliculas, series, personas))
    }

}