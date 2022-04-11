package com.github.bgrebennikov.devbuff.presentation.ui.customViews

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.github.bgrebennikov.devbuff.R
import com.github.bgrebennikov.devbuff.common.STAGED_BASE_IMAGE_URL
import com.github.bgrebennikov.devbuff.databinding.AppbarIdeaDetailsBinding

class AppbarIdeaDetails @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    private var _binding: AppbarIdeaDetailsBinding? = null
    val binding get() = _binding!!


    private val viewAttrs: TypedArray by lazy {
        context.obtainStyledAttributes(
            attrs,
            R.styleable.AppbarIdeaDetails,
            0, 0
        )
    }

    init {

        _binding = AppbarIdeaDetailsBinding.bind(
            LayoutInflater.from(context)
                .inflate(
                    R.layout.appbar_idea_details,
                    this,
                    true
                )
        )

        setupAppBar()
    }

    fun onBackPressed(callback: () -> Unit) {
        binding.backIcon.setOnClickListener { callback.invoke() }
    }

    fun setImageFromUserId(userId: String?) {

        if (userId == null) return

        val imageUrl = "%s/%s".format(STAGED_BASE_IMAGE_URL, userId)

        runCatching {
            Glide.with(context)
                .load(imageUrl)
                .transition(
                    withCrossFade()
                )
                .into(binding.userAvatarImage)
        }

    }

    fun setTitle(title: String?) {
        binding.title.text = title
    }

    fun setSubtitle(subtitle: String?) {
        binding.subtitle.text = subtitle
    }


    private fun setupAppBar() {
        binding.apply {
            setTitle(viewAttrs.getString(R.styleable.AppbarIdeaDetails_title))
            setSubtitle(viewAttrs.getString(R.styleable.AppbarIdeaDetails_subtitle))
            setImageFromUserId(viewAttrs.getString(R.styleable.AppbarIdeaDetails_imageFromUserId))
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        Glide.with(context).clear(binding.userAvatarImage)
        _binding = null


    }

}