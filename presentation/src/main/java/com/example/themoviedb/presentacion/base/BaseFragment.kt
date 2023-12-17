package com.example.themoviedb.presentacion.base


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.themoviedb.presentacion.ui.MainActivity

abstract class BaseFragment<ViewBinding : ViewDataBinding>(@LayoutRes protected val contentLayoutId: Int) :
    Fragment() {

    private var _binding: ViewBinding? = null

    protected val binding: ViewBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, contentLayoutId, container, false)
        return binding.root
    }

    open fun configureToolbar() = MainActivity.ToolbarConfiguration()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUi()
        observerViewModel()
        (requireActivity() as MainActivity).apply {
            hideProgress()
            setToolbarConfiguration(configureToolbar())
        }
    }

    protected abstract fun setUpUi()

    open fun observerViewModel() {
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}