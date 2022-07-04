package com.jbavaji.preparationapp.utils

import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan

fun SpannableString.SetStringAsSpannable(stringSpan: String, colorSpan: Int): SpannableString {
    val start = this.toString().indexOf(stringSpan, 0, true)
    val end = start + stringSpan.length
    this.setSpan(ForegroundColorSpan(colorSpan), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    return this
}
