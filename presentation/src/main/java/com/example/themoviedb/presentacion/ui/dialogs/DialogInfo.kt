package com.example.themoviedb.presentacion.ui.dialogs

import android.os.Bundle
import android.view.*
import com.example.themoviedb.R
import com.example.themoviedb.databinding.AlertDialogInfoBinding
import com.example.themoviedb.presentacion.base.BaseDialog


class DialogInfo(
    private val kindOfMessage: Int = SUCCES_MESSAGE,
    private val messageBody: String = "",
    private val clickOnAccept: ClickOnAccept? = null,
    private val isTwoButtonDialog: Boolean = false
) :
    BaseDialog<AlertDialogInfoBinding>(R.layout.alert_dialog_info) {


    companion object {
        const val SUCCES_MESSAGE_COLOR = R.color.succes
        const val WARNING_MESSAGE_COLOR = R.color.waring
        const val ERROR_MESSAGE_COLOR = R.color.danger
        const val INFO_MESSAGE_COLOR = R.color.info
        const val SUCCES_MESSAGE = 0
        const val WARNING_MESSAGE = 1
        const val ERROR_MESSAGE = 2
        const val INFO_MESSAGE = 3
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUi()
    }

    override fun setUpUi() {
        with(binding) {
            buttonAceptarOneButton.setOnClickListener {
                clickOnAccept?.clickOnAccept()
                dialog?.dismiss()
            }
            buttonAceptar.setOnClickListener {
                clickOnAccept?.clickOnAccept()
                dialog?.dismiss()
            }
            buttonCancelar.setOnClickListener {
                clickOnAccept?.clickOnCancel()
                dialog?.dismiss()
            }
            binding.bodyMessage.text = messageBody
        }
        setKindOfMessage()
        setKindOfView(isTwoButtonDialog)
    }

    interface ClickOnAccept {
        fun clickOnAccept()
        fun clickOnCancel()
    }

    private fun setKindOfView(isTwoButtonDialog: Boolean) {
        if (isTwoButtonDialog) {
            with(binding) {
                buttonAceptarOneButton.visibility = View.GONE
                buttonAceptar.visibility = View.VISIBLE
                buttonCancelar.visibility = View.VISIBLE
            }
        } else {
            with(binding) {
                buttonAceptarOneButton.visibility = View.VISIBLE
                buttonAceptar.visibility = View.GONE
                buttonCancelar.visibility = View.GONE
            }
        }
    }

    private fun setKindOfMessage() {
        when (kindOfMessage) {
            0 -> {
                binding.headerDialog.setCardBackgroundColor(resources.getColor(SUCCES_MESSAGE_COLOR))
                binding.titleHeader.text = "Succes"
            }
            1 -> {
                binding.headerDialog.setCardBackgroundColor(resources.getColor(WARNING_MESSAGE_COLOR))
                binding.titleHeader.text = "Warning"
            }
            2 -> {
                binding.headerDialog.setCardBackgroundColor(resources.getColor(ERROR_MESSAGE_COLOR))
                binding.titleHeader.text = "Error"
            }
            3 -> {
                binding.headerDialog.setCardBackgroundColor(resources.getColor(INFO_MESSAGE_COLOR))
                binding.titleHeader.text = "Info"
            }
        }
    }


}