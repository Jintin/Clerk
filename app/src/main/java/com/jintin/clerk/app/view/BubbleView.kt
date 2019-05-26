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

        fun onBubbleDragStart(x: Int, y: Int)

        fun onBubbleDragEnd()

        fun onBubbleMinimize(minimize: Boolean)

        fun onBubbleMove(x: Int, y: Int)

    }

    private var baseCount: Int = -1
    private var newCount: Int = -1
    private var isMinimize = true
    private lateinit var bubbleActionListener: OnBubbleActionListener
    private var startX: Int = 0
    private var startY: Int = 0
    private var radius = (32 * resources.displayMetrics.density).toInt()

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
        setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    startX = event.rawX.toInt()
                    startY = event.rawY.toInt()
                    bubbleActionListener.onBubbleDragStart(
                        startX - event.x.toInt() + radius,
                        startY - event.y.toInt() + radius
                    )
                }
                MotionEvent.ACTION_MOVE -> {
                    bubbleActionListener.onBubbleMove(event.rawX.toInt(), event.rawY.toInt())
                }
                MotionEvent.ACTION_UP,
                MotionEvent.ACTION_CANCEL -> {
                    if (Math.abs(event.rawX.toInt() - startX) + Math.abs(event.rawY.toInt() - startY) > 50) {
                        bubbleActionListener.onBubbleDragEnd()
                        return@setOnTouchListener true
                    }
                }
            }

            return@setOnTouchListener false
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