package com.example.themoviedb.presentacion.ui.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.example.themoviedb.R
import com.example.domain.state.Result
import com.example.themoviedb.presentacion.ui.MainActivity
import com.example.themoviedb.presentacion.ui.dialogs.DialogInfo
import com.example.themoviedb.presentacion.ui.main.AlertDialogs

fun Fragment.showErrorApi(wantExitFromView: Boolean = false) {
    val dialog = AlertDialogs(DialogInfo.ERROR_MESSAGE, getString(R.string.error_service))
    activity?.let { dialog.show(it.supportFragmentManager, "alertMessage") }
    if (wantExitFromView) {
        findNavController().popBackStack()
    }
}

fun Fragment.showErrorNetwork() {
    val dialog = AlertDialogs(DialogInfo.ERROR_MESSAGE, getString(R.string.verifica_conexion))
    activity?.let { dialog.show(it.supportFragmentManager, "alertMessage") }
}

fun Fragment.shouldShowProgress(isLoading: Boolean) {
    (requireActivity() as MainActivity).shouldShowProgress(isLoading)
}

fun Fragment.showToolbar() {
    (requireActivity() as MainActivity).showToolbar(true)
}

fun Fragment.changeTitleToolbar(title: String) {
    (requireActivity() as MainActivity).changeTextToolbar(title)
}

fun Fragment.getPackageName() = context?.packageName.toString()

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Fragment.navigate(action: NavDirections) {
    findNavController().navigate(action)
}

fun <T> Fragment.observeApiResult(
    liveData: LiveData<Result<T>>,
    onLoading: () -> Unit = {},
    onFinishLoading: () -> Unit = { },
    hasProgressTheView: Boolean = true,
    shouldCloseTheViewOnApiError: Boolean = false,
    emptyList: () -> Unit = {},
    onError: (() -> Unit)? = null,
    onSuccess: (data: T) -> Unit,
) {
    liveData.observe(viewLifecycleOwner) {
        fun handleStatusOnLoading(isLoading: Boolean) {
            if (isLoading) {
                onLoading()
            } else {
                onFinishLoading()
            }
        }

        val isLoading = it is Result.Loading
        handleStatusOnLoading(isLoading)
        if (hasProgressTheView) {
            shouldShowProgress(isLoading)
        }
        when (it) {
            is Result.Success -> {
                onSuccess(it.data)
            }

            is Result.EmptyList -> {
                emptyList()
            }

            is Result.Error -> {
                if (onError == null) {
                    showErrorApi(shouldCloseTheViewOnApiError)
                } else {
                    onError()
                }
            }

            is Result.ErrorNetwork -> {
                showErrorNetwork()
            }

            else -> {}
        }
    }
}
