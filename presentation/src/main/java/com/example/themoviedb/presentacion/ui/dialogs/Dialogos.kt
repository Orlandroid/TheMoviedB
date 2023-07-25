package com.example.themoviedb.presentacion.ui.dialogs

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface

class Dialogos {

    fun showDialogMultiOption(context: Context, title: String = "Selecciona una opcion",opciones:Array<String>) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setItems(opciones, object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {

            }
        })
        val dialog = builder.create()
        dialog.show()
    }
}
