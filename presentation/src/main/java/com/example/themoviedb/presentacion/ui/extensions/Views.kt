package com.example.themoviedb.presentacion.ui.extensions

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.annotation.ColorRes


fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun ImageView.changeDrawableColor(color: Int) {
    this.setColorFilter(resources.getColor(color))
}

fun View.click(click: () -> Unit) {
    setOnClickListener { click() }
}

fun Context.getColor(@ColorRes color: Int): Int {
    return resources.getColor(color)
}

