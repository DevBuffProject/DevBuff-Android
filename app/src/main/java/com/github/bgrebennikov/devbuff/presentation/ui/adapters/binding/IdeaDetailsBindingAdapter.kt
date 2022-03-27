package com.github.bgrebennikov.devbuff.presentation.ui.adapters.binding

import android.annotation.SuppressLint
import android.graphics.Color
import android.text.format.DateUtils
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.github.bgrebennikov.devbuff.R
import com.github.bgrebennikov.devbuff.data.remote.models.explore.IdeaStatus
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