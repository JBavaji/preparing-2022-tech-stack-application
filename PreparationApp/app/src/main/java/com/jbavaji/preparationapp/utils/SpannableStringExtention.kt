package com.jbavaji.preparationapp.utils

import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View

fun SpannableString.SetStringAsSpannable(stringSpan: String, colorSpan: Int, actionOnClick: () -> Unit): SpannableString {
    val start = this.toString().indexOf(stringSpan, 0, true)
    val end = start + stringSpan.length
    val clickableSpan = object : ClickableSpan() {
        override fun updateDrawState(ds: TextPaint) {
            ds.color = colorSpan      // you can use custom color
            ds.isUnderlineText = false  // this remove the underline
        }
        override fun onClick(view: View) = actionOnClick()
    }

    this.setSpan(clickableSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    return this
}
