package com.andreivanceadev.sliidetechnical.ui

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import com.andreivanceadev.sliidetechnical.R

object DialogCreator {

    fun showDualChoiceDialog(
        context: Context,
        message: String,
        positiveBtn: Int,
        negativeBtn: Int,
        onPositive: DialogInterface.OnClickListener? = null,
        onNegative: DialogInterface.OnClickListener? = null
    ) {
        AlertDialog.Builder(context)
            .setMessage(message)
            .setPositiveButton(positiveBtn, onPositive)
            .setNegativeButton(negativeBtn, onNegative)
            .setCancelable(true)
            .create()
            .show()
    }

    fun showErrorDialog(context: Context, message: String) {
        AlertDialog.Builder(context)
            .setMessage(message)
            .setPositiveButton(R.string.ok, null)
            .setCancelable(true)
            .create()
            .show()
    }

}
