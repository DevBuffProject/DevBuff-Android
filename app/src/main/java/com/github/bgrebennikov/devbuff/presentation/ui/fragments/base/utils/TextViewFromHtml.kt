package com.github.bgrebennikov.devbuff.presentation.ui.fragments.base.utils

import android.content.res.Resources
import android.os.Build
import android.text.Html
import android.text.Spannable
import android.widget.TextView
import androidx.core.text.HtmlCompat
import com.github.bgrebennikov.devbuff.presentation.ui.fragments.base.GlideImageGetter
import kotlin.math.roundToInt
import kotlin.math.roundToLong

fun TextView.setTextFromHtml(
    textPadding: Int = 0,
    message: String,
    imageGetter: Html.ImageGetter = GlideImageGetter(this, textPadding.dpToPx()),
    tagHandler: Html.TagHandler? = null
) {
//    this.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//        Html.fromHtml(message, Html.FROM_HTML_MODE_LEGACY, imageGetter, tagHandler) as Spannable
//    } else {
//        @Suppress("DEPRECATION")
//        Html.fromHtml(message, imageGetter, tagHandler) as Spannable
//    }

    this.text = HtmlCompat.fromHtml(message, HtmlCompat.FROM_HTML_MODE_COMPACT, imageGetter, tagHandler)

}

fun Int.dpToPx() = convertDpToPixel(toFloat()).toInt()

fun convertDpToPixel(dp: Float): Float {
    val metrics = Resources.getSystem().displayMetrics
    val px = dp * (metrics.densityDpi / 160f)
    return px.roundToInt().toFloat()
}