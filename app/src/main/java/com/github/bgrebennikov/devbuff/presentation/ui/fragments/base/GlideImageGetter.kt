package com.github.bgrebennikov.devbuff.presentation.ui.fragments.base

import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.text.Html
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.Request
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SizeReadyCallback
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL
import com.bumptech.glide.request.transition.Transition
import com.github.bgrebennikov.devbuff.R
import java.lang.ref.WeakReference

class GlideImageGetter(
    textView: TextView,
    val textViewPadding: Int,
    private val density: Float = textView.resources.displayMetrics.density
) : Html.ImageGetter {

    private val container: WeakReference<TextView> = WeakReference(textView)

    override fun getDrawable(source: String?): Drawable? {

        val drawable = BitmapDrawablePlaceholder()

        // Load Image to the Drawable
        container.get()?.apply {
            post {
                runCatching {
                    Glide.with(context)
                        .asBitmap()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .load(Uri.parse(source))
                        .into(drawable)
                }
            }
        }

        return drawable
    }

    private inner class BitmapDrawablePlaceholder : BitmapDrawable(
        container.get()?.resources,
        Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565)
    ), Target<Bitmap> {


        val measureSize = container.get()?.measuredWidth ?: 0
        private var drawable: Drawable? = null


        private fun setDrawable(drawable: Drawable, width: Int? = null, height: Int? = null) {
            this.drawable = drawable
            this.drawable?.let { value ->

                val drawableWidth = width ?: (drawable.intrinsicWidth / 3 * density).toInt()
                val drawableHeight = height ?: (drawable.intrinsicHeight / 3 * density).toInt()

                val maxWidth = container.get()?.measuredWidth?.minus(textViewPadding) ?: 0

                if (drawableWidth > maxWidth) {
                    val calculatedHeight = maxWidth * drawableHeight / drawableWidth
                    value.setBounds(0, 0, maxWidth, calculatedHeight)
                    setBounds(0, 0, maxWidth, calculatedHeight)
                } else {
                    value.setBounds(0, 0, drawableWidth, drawableHeight)
                    setBounds(0, 0, drawableWidth, drawableHeight)
                }
                container.get()?.text = container.get()?.text
            }
        }

        override fun draw(canvas: Canvas) {
            drawable?.draw(canvas)
        }

        override fun onLoadStarted(placeholderDrawable: Drawable?) {
            setPlaceholder()
        }

        override fun onLoadFailed(errorDrawable: Drawable?) {

        }

        override fun onResourceReady(bitmap: Bitmap, transition: Transition<in Bitmap>?) {
            val transformedBitmap = Bitmap.createBitmap(
                bitmap.width,
                bitmap.height + (container.get()?.paint?.fontMetricsInt?.bottom ?: 0) * 2,
                Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(transformedBitmap)
            canvas.drawBitmap(
                bitmap,
                0f,
                container.get()?.paint?.fontMetricsInt?.bottom?.toFloat() ?: 0f,
                null
            )
            val imageDrawable = BitmapDrawable(
                container.get()?.resources ?: return,
                transformedBitmap
            )

            setDrawable(imageDrawable)
        }

        private fun setPlaceholder(placeholderBitmap: Bitmap? = null, placeholderColor: Int = Color.LTGRAY) {


            val transformedBitmap = Bitmap.createBitmap(
                measureSize,
                measureSize,
                Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(transformedBitmap)
            canvas.apply {
                if (placeholderBitmap != null) {
                    drawBitmap(
                        placeholderBitmap,
                        0f,
                        container.get()?.paint?.fontMetricsInt?.bottom?.toFloat() ?: 0f,
                        null
                    )
                } else{
                    val paint = Paint().apply {
                        color = placeholderColor
                        style = Paint.Style.FILL
                    }
                    drawPaint(
                        paint
                    )
                }
            }

            val imageDrawable = BitmapDrawable(
                container.get()?.resources,
                transformedBitmap
            )

            val drSize = (measureSize / 3 * density).toInt()

            setDrawable(imageDrawable, measureSize, drSize)

        }

        override fun onLoadCleared(placeholderDrawable: Drawable?) {
            placeholderDrawable?.let {
                drawable = it
            }
        }

        override fun getSize(sizeReadyCallback: SizeReadyCallback) {
            sizeReadyCallback.onSizeReady(SIZE_ORIGINAL, SIZE_ORIGINAL)
        }

        override fun removeCallback(cb: SizeReadyCallback) {}
        override fun setRequest(request: Request?) {}
        override fun getRequest(): Request? {
            return null
        }

        override fun onStart() {}
        override fun onStop() {}
        override fun onDestroy() {}
    }
}