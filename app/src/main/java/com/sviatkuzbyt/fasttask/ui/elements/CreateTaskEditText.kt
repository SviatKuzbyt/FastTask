package com.sviatkuzbyt.fasttask.ui.elements

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo

class CreateTaskEditText(context: Context?, attrs: AttributeSet?) :
    androidx.appcompat.widget.AppCompatEditText(context!!, attrs) {

    init {
        imeOptions = EditorInfo.IME_ACTION_DONE
        setRawInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE)
        setRawInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES)
    }

    override fun onKeyPreIme(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) clearFocus()
        return false
    }
}