package com.batman.example.presentation.misc

import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.EditText

class EditTextPopUpKeyboard : EditText {

    // <editor-fold desc="Prepare a listener that can be provided to respond to back pressed events">
    private var listener: OnBackPressedListener? = null

    interface OnBackPressedListener {
        fun backPressed()
    }

    fun setOnBackPressedListener(listener: OnBackPressedListener) {
        this.listener = listener
    }

    override fun onKeyPreIme(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_UP) {
            if (listener != null) {
                listener!!.backPressed()
            }
            return false
        }
        return super.dispatchKeyEvent(event)
    }
    // </editor-fold>

    // <editor-fold desc="Define class constructors">
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        addKeyboardOptions()
    }

    constructor(context: Context) : super(context) {
        addKeyboardOptions()
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        addKeyboardOptions()
    }
    // </editor-fold>

    // <editor-fold desc="Set the IME options for an EditTextPopUpKeyboard">
    private fun addKeyboardOptions() {
        this.imeOptions = EditorInfo.IME_FLAG_NO_EXTRACT_UI or EditorInfo.IME_ACTION_DONE
    }
    // </editor-fold>
}
