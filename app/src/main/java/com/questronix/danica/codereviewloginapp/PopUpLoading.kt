package com.questronix.danica.codereviewloginapp

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable

class PopUpLoading (context: Context) {

    private lateinit var progressDialog: ProgressDialog
    val context = context

    fun show() {
        progressDialog = ProgressDialog(context)
        progressDialog.setCancelable(false)
        progressDialog.show()
        progressDialog.setContentView(R.layout.popuploading_dialog)
        progressDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    fun dismiss() {
        progressDialog.dismiss()
    }

    fun isShowing(): Boolean {
        return if (progressDialog.isShowing) {
            true
        } else {
            false
        }
    }

}
