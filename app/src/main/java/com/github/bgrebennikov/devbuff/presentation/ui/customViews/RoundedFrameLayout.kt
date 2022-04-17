package com.github.bgrebennikov.devbuff.presentation.ui.customViews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.widget.FrameLayout
import com.github.bgrebennikov.devbuff.R

class RoundedFrameLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(
    context, attrs, defStyle
) {

    private var path: Path? = null
    private var cornerTopLeftRadius: Int = 0
    private var cornerTopRightRadius: Int = 0
    private var cornerBottomLeftRadius: Int = 0
    private var cornerBottomRightRadius: Int = 0

    init {
        val a = context.theme.obtainStyledAttributes(
            attrs, R.styleable.RoundedFrameLayout,
            0, 0
        )

        val cornersRadius = a.getDimensionPixelSize(R.styleable.RoundedFrameLayout_cornerRadius, 0)

        cornerTopLeftRadius = a.getDimensionPixelSize(R.styleable.RoundedFrameLayout_cornerRadiusTopLeft, cornersRadius)
        cornerTopRightRadius = a.getDimensionPixelSize(R.styleable.RoundedFrameLayout_cornerRadiusTopRight, cornersRadius)
        cornerBottomLeftRadius = a.getDimensionPixelSize(R.styleable.RoundedFrameLayout_cornerRadiusBottomLeft, cornersRadius)
        cornerBottomRightRadius = a.getDimensionPixelSize(R.styleable.RoundedFrameLayout_cornerRadiusBottomRight, cornersRadius)

        a.recycle()

    }

    fun setCornersRadius(radius: Int){
        cornerTopLeftRadius = radius
        cornerTopRightRadius = radius
        cornerBottomLeftRadius = radius
        cornerBottomRightRadius = radius

        invalidate()
    }

    fun setCornersRadiusTopLeft(radius: Int){
        cornerTopLeftRadius = radius
        invalidate()
    }

    fun setCornersRadiusTopRight(radius: Int){
        cornerTopRightRadius = radius
        invalidate()
    }

    fun setCornersRadiusBottomLeft(radius: Int){
        cornerBottomLeftRadius = radius
        invalidate()
    }

    fun setCornersRadiusBottomRight(radius: Int){
        cornerBottomRightRadius = radius
        invalidate()
    }

    override fun draw(canvas: Canvas) {
        canvas.save()
        path?.let {
            canvas.clipPath(it)
        }
        super.draw(canvas)
        canvas.restore()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        val rect = RectF(0f, 0f, w.toFloat(), h.toFloat())
        path = Path().apply {
            addRoundRect(
                rect, floatArrayOf(
                    cornerTopLeftRadius.toFloat(),
                    cornerTopLeftRadius.toFloat(),
                    cornerTopRightRadius.toFloat(),
                    cornerTopRightRadius.toFloat(),
                    cornerBottomLeftRadius.toFloat(),
                    cornerBottomLeftRadius.toFloat(),
                    cornerBottomRightRadius.toFloat(),
                    cornerBottomRightRadius.toFloat()
                    ), Path.Direction.CW
            )
            close()
        }

    }

}