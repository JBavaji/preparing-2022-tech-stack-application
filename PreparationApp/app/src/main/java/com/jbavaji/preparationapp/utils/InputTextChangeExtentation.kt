package com.jbavaji.preparationapp.utils

import android.widget.EditText
import androidx.core.widget.doAfterTextChanged

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    doAfterTextChanged {
        afterTextChanged.invoke(it.toString())
    }
}
