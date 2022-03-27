package com.github.bgrebennikov.devbuff.presentation.ui.fragments.base.utils

import android.content.res.Resources
import android.os.Build
import android.text.Html
import android.text.Spannable
import android.widget.TextView
import com.github.bgrebennikov.devbuff.presentation.ui.fragments.base.GlideImageGetter
import kotlin.math.roundToInt
import kotlin.math.roundToLong

fun TextView.setTextFromHtml(
    message: String,
    imageGetter: Html.ImageGetter = GlideImageGetter(this),
    tagHandler: Html.TagHandler? = null
) {
    this.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(message, Html.FROM_HTML_MODE_LEGACY, imageGetter, tagHandler) as Spannable
    } else {
        @Suppress("DEPRECATION")
        Html.fromHtml(message, imageGetter, tagHandler) as Spannable
    }
}

fun Int.dpToPx() = convertDpToPixel(toFloat()).toInt()

fun convertDpToPixel(dp: Float): Float {
    val metrics = Resources.getSystem().displayMetrics
    val px = dp * (metrics.densityDpi / 160f)
    return px.roundToInt().toFloat()
}