package com.jintin.clerk.app.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.jintin.clerk.app.R
import kotlinx.android.synthetic.main.view_bubble.view.*

/**
 * Bubble View for Clerk log
 */
class BubbleView : ConstraintLayout {

    interface OnBubbleActionListener {
        fun onBubbleMinimize(minimize: Boolean)

        fun onBubbleMove(x: Int, y: Int)
    }

    private var baseCount: Int = -1
    private var newCount: Int = -1
    private var isMinimize = true
    private var bubbleActionListener: OnBubbleActionListener? = null
    private var startX: Int = 0
    private var startY: Int = 0

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        View.inflate(context, R.layout.view_bubble, this)
    }

    fun setOnBubbleActionListener(listener: OnBubbleActionListener) {
        bubbleActionListener = listener
        setOnClickListener {
            setMinimize(!isMinimize)
        }
        setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    Log.e("jintin", "touch donw")
                    startX = event.x.toInt()
                    startY = event.y.toInt()
                }
                MotionEvent.ACTION_MOVE -> {
                    Log.e("jintin", "touch move")
                    bubbleActionListener?.onBubbleMove(event.x.toInt() - startX, event.y.toInt() - startY)
                    startX = event.x.toInt()
                    startY = event.y.toInt()
                }
            }

            false
        }
    }

    fun setCount(newCount: Int) {
        if (baseCount == -1) {
            baseCount = newCount
        }

        this.newCount = newCount
        updateCount()
    }

    private fun updateCount() {
        (newCount - baseCount).let {
            if (it < 100) {
                count.text = it.toString()
            } else {
                count.text = context.getString(R.string.max_new_log)
            }
            count.visibility = if (it == 0) View.INVISIBLE else View.VISIBLE
        }
    }

    fun isMinimize(): Boolean {
        return isMinimize
    }

    fun setMinimize(minimize: Boolean) {
        isMinimize = minimize
        bubbleActionListener?.onBubbleMinimize(isMinimize)
        if (!minimize) {
            baseCount = newCount
            updateCount()
        }
        Log.e("jintin", " touch")
    }

}