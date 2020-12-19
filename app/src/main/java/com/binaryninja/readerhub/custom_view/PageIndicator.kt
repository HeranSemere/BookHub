package com.binaryninja.readerhub.custom_view

import android.content.Context
import android.graphics.Color
import android.icu.util.MeasureUnit
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import java.util.*

class PageIndicator : LinearLayout {
    var views: MutableList<AppCompatTextView> = LinkedList()
    var totalPage = 5

    constructor(context: Context?, pageCount: Int) : super(context) {
        totalPage = pageCount
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
        orientation = HORIZONTAL
        layoutParams = LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        removeAllViews()
        for (i in 0 until totalPage) {
            val view = AppCompatTextView(context)
            view.layoutParams = LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            view.text = "○"
            view.setTextColor(Color.BLACK)
            //view.setTextColor(Color.parseColor("#883D3D3D"));
            view.setTextSize(TypedValue.COMPLEX_UNIT_SP,17f)
            view.setPadding(15, 0, 15, 0)
            views.add(view)
            addView(view)
        }
        setCurrentPage(0)
    }

    fun setCurrentPage(currentPage: Int) {
        changeColor(currentPage)
    }

    private fun changeColor(pos: Int) {
        for (i in views.indices) {
            views[i].setTextColor(Color.parseColor("#883D3D3D"))
        }
        views[pos].setTextColor(Color.WHITE)
    }
}