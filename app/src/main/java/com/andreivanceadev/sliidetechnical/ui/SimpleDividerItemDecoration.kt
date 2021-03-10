package com.andreivanceadev.sliidetechnical.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.andreivanceadev.sliidetechnical.R
import toothpick.Toothpick

class SimpleDividerItemDecoration(context: Context, private val decorationStart: Int = 0) : RecyclerView.ItemDecoration() {

    private val divider: Drawable

    init {
        Toothpick.openScope(context).inject(this)
        divider = ContextCompat.getDrawable(context, R.drawable.line_separator)!!
    }

    override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight
        val decorationEnd = parent.childCount

        for (currentViewNumber in decorationStart until decorationEnd) {
            val child = parent.getChildAt(currentViewNumber)

            drawTop(canvas, left, right, child)

            if (isLastItem(child, parent, state)) drawBottom(canvas, left, right, child)
        }
    }

    private fun drawTop(canvas: Canvas, left: Int, right: Int, child: View) {
        val params = child.layoutParams as? RecyclerView.LayoutParams ?: return
        val top = child.top - params.topMargin
        val bottom = top + divider.intrinsicHeight
        divider.setBounds(left, top, right, bottom)
        divider.draw(canvas)
    }

    private fun drawBottom(canvas: Canvas, left: Int, right: Int, child: View) {
        val params = child.layoutParams as? RecyclerView.LayoutParams ?: return
        val bottom = child.bottom + params.bottomMargin
        val top = bottom - divider.intrinsicHeight
        divider.setBounds(left, top, right, bottom)
        divider.draw(canvas)
    }

    private fun isLastItem(view: View, parent: RecyclerView, state: RecyclerView.State): Boolean {
        return parent.getChildAdapterPosition(view) == state.itemCount - 1
    }
}
