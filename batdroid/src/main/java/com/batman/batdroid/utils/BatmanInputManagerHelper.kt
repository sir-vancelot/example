package com.batman.batdroid.utils

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.view.inputmethod.InputMethodManager

/**
 * Used to track and hide various ui input methods.
 */
object BatmanInputManagerHelper {
    /**
     * Used to hold a reference to a progress dialog.
     */
    private var progressDialog: ProgressDialog? = null

    /**
     * Used to dismiss the keyboard.
     */
    fun hideKeyboard(activity: Activity?) {
        val imm = activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
    }

    /**
     * Used to present a progress dialog to the user.
     */
    fun showProgressDialog(context: Context, message: String) {
        if (progressDialog == null) {
            progressDialog = ProgressDialog(context)
            progressDialog!!.isIndeterminate = true
            progressDialog!!.setMessage(message)
            progressDialog!!.show()
        } else {
            progressDialog!!.setMessage(message)
        }
    }

    /**
     * Used to dismiss a progress dialog.
     */
    fun hideProgressDialog() {
        if (progressDialog != null) {
            progressDialog!!.dismiss()
        }
        progressDialog = null
    }
}
