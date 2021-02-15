package com.viktor.kh.dev.exchangerates.utils.customs


import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.viktor.kh.dev.exchangerates.data.GraphData


class CustomGraph @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttrs:Int = 0) :View(context,attrs,defStyleAttrs) {

    private var paint = Paint()




    var widthLine = mutableListOf<Int>()
    var heightLine = mutableListOf<Int>()




     public fun initData(data: List<GraphData>){
         // create list for drawing

         paint.color = Color.BLUE
         paint.strokeWidth = 5f


         val width = getWidth()
         val height = getHeight()

        var list = data.sortedBy {it.date}

         var pxTime = width/(list[list.size-1].date - list[0].date).toInt()

         widthLine.add(0)
         for ( i in 1 until list.size){
             widthLine.add(((list[i].date - list[i-1].date)*pxTime).toInt())
         }
         list  = data.sortedBy {it.value}

         var pxValue = height/((list[list.size-1].value - list[0].value)*100)
         list = data.sortedBy {it.date}



         heightLine.add(height-(((list[0].value)*100)*pxValue).toInt())
         for ( i in 1 until list.size){
             heightLine.add(height-(((list[i].value - list[i-1].value)*100)*pxValue).toInt())
         }



     }







    override fun onDraw(canvas: Canvas?) {
         //drawing a graph for currency
         for (i in 0 until widthLine.size-1){
             if (canvas != null) {
                 canvas.drawLine(widthLine[i].toFloat(),
                     widthLine[i+1].toFloat(), heightLine[i].toFloat(), heightLine[i+1].toFloat(),paint)
             }
         }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        var measureWidth = MeasureSpec.getSize(widthMeasureSpec)-(paddingStart+paddingEnd)
        var measureHeight = MeasureSpec.getSize(heightMeasureSpec)-(paddingTop+paddingBottom)

        setMeasuredDimension(measuredWidth,measuredHeight)
    }

}