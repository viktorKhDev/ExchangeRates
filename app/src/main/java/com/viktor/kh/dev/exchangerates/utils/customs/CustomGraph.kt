package com.viktor.kh.dev.exchangerates.utils.customs


import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View



class CustomGraph @JvmOverloads constructor(context: Context, attrs: AttributeSet) :View(context,attrs) {

    private var paint = Paint()

    override fun onDraw(canvas: Canvas?) {

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        var measureWidth = MeasureSpec.getSize(widthMeasureSpec)-(paddingStart+paddingEnd)
        var measureHeight = MeasureSpec.getSize(heightMeasureSpec)-(paddingTop+paddingBottom)

        setMeasuredDimension(measuredWidth,measuredHeight)
    }




}