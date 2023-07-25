package com.example.themoviedb.presentacion.helpers

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.themoviedb.presentacion.util.ImageUtil
import java.io.File
import java.io.FileOutputStream

fun CharSequence.isEmail() = isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun Fragment.shortToast(text: String) {
    Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
}

fun Fragment.longToast(text: String) {
    Toast.makeText(requireContext(), text, Toast.LENGTH_LONG).show()
}

fun Fragment.allowOptionsMenu(hasOptionsMenu: Boolean = true) {
    setHasOptionsMenu(hasOptionsMenu)
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun ImageView.loadImage(url: String, posterSize: String = ImageUtil.IMAGE_500) {
    val imageUtil = ImageUtil()
    Glide.with(this.context).load(imageUtil.getBaseUrlImagePoster(urlImage = url, posteSize = posterSize)).into(this)
    Log.i("android",imageUtil.getBaseUrlImagePoster(urlImage = url, posteSize = posterSize))
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.getBitmap(): Bitmap {
    val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    draw(canvas)
    canvas.save()
    return bitmap
}

fun SearchView.disableSearchIconHint() {
    try {
        val mDrawable = SearchView::class.java.getDeclaredField("mSearchHintIcon")
        mDrawable.isAccessible = true
        val drawable = mDrawable.get(this) as Drawable
        drawable.setBounds(0, 0, 0, 0)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun Bitmap.saveFile(path: String): File {
    val f = File(path)
    if (!f.exists()) {
        f.createNewFile()
    }
    val stream = FileOutputStream(f)
    compress(Bitmap.CompressFormat.PNG, 100, stream)
    stream.flush()
    stream.close()
    return f
}