package com.github.bgrebennikov.devbuff.presentation.ui.adapters.binding

import android.annotation.SuppressLint
import android.text.format.DateUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.github.bgrebennikov.devbuff.R
import com.github.bgrebennikov.devbuff.common.STAGED_BASE_IMAGE_URL
import com.github.bgrebennikov.devbuff.data.remote.models.explore.IdeaStatus
import com.github.bgrebennikov.devbuff.presentation.ui.fragments.base.utils.setTextFromHtml
import java.text.SimpleDateFormat

@SuppressLint("SimpleDateFormat")
@BindingAdapter("ideaLastUpdateTimeString")
fun setIdeaLastUpdateTimeString(textView: TextView, dateTime: String) {

    val datePattern = SimpleDateFormat("yyyy-MM-dd")
    val parsedDate = datePattern.parse(dateTime)?.time

    with(textView){
        text = if(parsedDate != null){
            DateUtils.formatDateTime(
                context,
                parsedDate,
                DateUtils.FORMAT_SHOW_DATE
            )
        } else { dateTime }
    }

}

@BindingAdapter("ideaStatus")
fun setIdeaStatus(textView: TextView, status: IdeaStatus?){

    with(textView){
        when(status){
            IdeaStatus.WAITING_FULL_TEAM -> {
                text = context.getString(R.string.waiting_full_team)
                setTextColor(context.getColor(R.color.green))
            }
            IdeaStatus.PUBLISH -> {
                text = context.getString(R.string.published)
                setTextColor(context.getColor(R.color.blue))
            }
            else -> {
                text = context.getString(R.string.ellipsize)
            }
        }
    }

}

@BindingAdapter("ideaThumbnailPhotoFromUser")
fun setIdeaThumbnailPhotoFromUser(imageView: ImageView, userId: String?){

    val context = imageView.context
    val imageUrl = "$STAGED_BASE_IMAGE_URL/$userId"

    runCatching {
        Glide.with(context)
            .load(imageUrl)
            .override(context.resources.getDimensionPixelOffset(R.dimen.profile_image_small))
            .into(imageView)
    }

}


@BindingAdapter("htmlText")
fun setHtmlText(textView: TextView, source: String?){

    source?.let {
        textView.setTextFromHtml(textView.paddingEnd, it)
    }

}

@BindingAdapter("hideIfNull")
fun setHideIfNull(textView: TextView, isEmpty: Boolean?){

    textView.apply {
        visibility = if(isEmpty == true) View.GONE else View.VISIBLE
    }

}

@BindingAdapter("showIfEmpty")
fun setShowIfEmpty(textView: TextView, isEmpty: Boolean?){
    textView.apply {
        visibility = if(isEmpty == true) View.VISIBLE else View.GONE
    }
}