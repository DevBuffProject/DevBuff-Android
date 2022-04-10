package com.github.bgrebennikov.devbuff.presentation.ui.adapters.binding

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.github.bgrebennikov.devbuff.R
import com.github.bgrebennikov.devbuff.common.STAGED_BASE_IMAGE_URL
import com.github.bgrebennikov.devbuff.presentation.ui.customViews.AppbarIdeaDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

@BindingAdapter(
    value = [
        "profileImageFromUserID"
    ],
    requireAll = false
)
fun setProfileImage(imageView: ImageView, userId: String?){

    val context = imageView.context
    val imageUrl = "$STAGED_BASE_IMAGE_URL/$userId"

    runCatching {
        Glide.with(context)
            .load(imageUrl)
            .transform(
                CenterCrop(),
                RoundedCorners(
                    context.resources.getDimensionPixelOffset(R.dimen.corners_radius_large)
                )
            )
            .transition(withCrossFade())
            .override(
                context.resources.getDimensionPixelOffset(R.dimen.profile_image_size)
            )
            .into(imageView)
    }

}

@BindingAdapter("username")
fun setUsername(textView: TextView, username: String?){
    with(textView){
        text = context.getString(R.string.username_prefix).format(username)
    }

}

@BindingAdapter("appbarUsername")
fun setAppbarUsername(appbar: AppbarIdeaDetails, username: String?){
    username?.let {
        appbar.setSubtitle(
            "@%s".format(it)
        )
    }
}

@BindingAdapter(
    value = ["firstName", "lastName"],
    requireAll = false
)
fun setFullName(textView: TextView, firstName: String?, lastName: String?){
    textView.text = "%s %s".format(
        firstName ?: "",
        lastName ?: ""
    )
}

@BindingAdapter("ideaDetailsLoading")
fun ideaDetailsLoading(progressBar: ProgressBar, isLoading: Boolean){
    progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
}
