package com.example.themoviedb.presentacion.ui.extensions

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.Base64
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.themoviedb.R
import com.example.themoviedb.domain.state.Result
import com.example.themoviedb.presentacion.ui.MainActivity
import com.example.themoviedb.presentacion.ui.dialogs.DialogInfo.Companion.ERROR_MESSAGE
import com.example.themoviedb.presentacion.ui.main.AlertDialogs
import com.google.android.material.snackbar.Snackbar
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.util.*


fun View.displaySnack(
    message: String,
    length: Int = Snackbar.LENGTH_LONG,
    func: Snackbar.() -> Unit
) {
    val snackBar = Snackbar.make(this, message, length)
    snackBar.func()
    snackBar.show()
}


fun Fragment.showErrorApi(wantExitFromView: Boolean = false) {
    val dialog = AlertDialogs(ERROR_MESSAGE, getString(R.string.error_service))
    activity?.let { dialog.show(it.supportFragmentManager, "alertMessage") }
    if (wantExitFromView) {
        findNavController().popBackStack()
    }
}

fun Fragment.showErrorNetwork() {
    val dialog = AlertDialogs(ERROR_MESSAGE, getString(R.string.verifica_conexion))
    activity?.let { dialog.show(it.supportFragmentManager, "alertMessage") }
}

fun <T> Fragment.observeApiResult(
    apiResult: LiveData<Result<T>>,
    onLoading: () -> Unit = {},
    onSuccess: () -> Unit = {},
    emptyList: () -> Unit = {},
    error: () -> Unit = {},
    errorNetwork: () -> Unit = {}
) {
    apiResult.observe(viewLifecycleOwner) {
        if (it is Result.Loading) {
            onLoading()
            (requireActivity() as MainActivity).showProgress()
        } else {
            (requireActivity() as MainActivity).hideProgress()
        }
        when (it) {
            is Result.Success -> {
                onSuccess()
            }
            is Result.EmptyList -> {
                emptyList()
            }
            is Result.Error -> {
                error()
                showErrorApi()
            }
            is Result.ErrorNetwork -> {
                errorNetwork()
                showErrorNetwork()
            }
            else -> {}
        }
    }
}

fun View.showSnack(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()
}

fun Snackbar.action(message: String, listener: (View) -> Unit) {
    setAction(message, listener)
}


fun Fragment.navigate(accion: NavDirections) {
    findNavController().navigate(accion)
}

fun View.navigate(accion: NavDirections) {
    findNavController().navigate(accion)
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun ImageView.setColorFilterImage(context: Context, @ColorRes colorInt: Int) {
    this.setColorFilter(
        ContextCompat.getColor(context, colorInt),
        PorterDuff.Mode.SRC_OVER
    )
}

@RequiresApi(Build.VERSION_CODES.M)
fun Drawable.tint(context: Context, @ColorRes color: Int) {
    DrawableCompat.setTint(this, context.resources.getColor(color, context.theme))
}

fun Bitmap.toBase64(): String {
    var result = ""
    val baos = ByteArrayOutputStream()
    try {
        compress(Bitmap.CompressFormat.JPEG, 100, baos)
        baos.flush()
        baos.close()
        val bitmapBytes = baos.toByteArray()
        result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT)
    } catch (e: IOException) {
        e.printStackTrace()
    } finally {
        try {
            baos.flush()
            baos.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
    return result
}



